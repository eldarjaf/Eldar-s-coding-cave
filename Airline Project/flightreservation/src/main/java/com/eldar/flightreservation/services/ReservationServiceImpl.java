package com.eldar.flightreservation.services;

import com.eldar.flightreservation.dto.ReservationRequest;
import com.eldar.flightreservation.entities.Flight;
import com.eldar.flightreservation.entities.Passenger;
import com.eldar.flightreservation.entities.Reservation;
import com.eldar.flightreservation.repos.FlightRepository;
import com.eldar.flightreservation.repos.PassengerRepository;
import com.eldar.flightreservation.repos.ReservationRepository;
import com.eldar.flightreservation.utilities.EmailUtil;
import com.eldar.flightreservation.utilities.PDFGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PDFGenerator pdfGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Value("${com.eldar.flightreservation.itinerary.dirpath}")
    private String ITINERARY_DIR;

    @Override
    public Reservation bookFlight(ReservationRequest request) {
        log.info("Inside bookFlight()");
        request.getCardNumber();
        //Make Payment, but we do not forward to any 3rd party gateway

        Long flightId = request.getFlightId();
        log.info("Fetching flight ID: " + flightId);
        Flight flight = flightRepository.findById(flightId).get();
        Passenger passenger = Passenger.builder()
                .firstName(request.getPassengerFirstName())
                .lastName(request.getPassengerLastName())
                .email(request.getPassengerEmail())
                .phone(request.getPassengerPhone())
                .build();
        log.info("Saving the Passenger: " + passenger);
        Passenger savedPassenger = passengerRepository.save(passenger);
        Reservation reservation = Reservation.builder()
                .flight(flight)
                .passenger(savedPassenger)
                .checkedIn(false)
                .build();

        Reservation savedReservation = reservationRepository.save(reservation);
        log.info("Saving the reservation: " + reservation);

        String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";
        pdfGenerator.generateItinerary(savedReservation, filePath);
        log.info("Generating the iterenary");
        emailUtil.sendItinerary(passenger.getEmail(), filePath);
        log.info("Emailing the iterenary");

        return savedReservation;
    }
}
