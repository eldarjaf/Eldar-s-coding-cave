package com.eldar.flightreservation.services;

import com.eldar.flightreservation.dto.ReservationRequest;
import com.eldar.flightreservation.entities.Flight;
import com.eldar.flightreservation.entities.Passenger;
import com.eldar.flightreservation.entities.Reservation;
import com.eldar.flightreservation.repos.FlightRepository;
import com.eldar.flightreservation.repos.PassengerRepository;
import com.eldar.flightreservation.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation bookFlight(ReservationRequest request) {
        request.getCardNumber();
        //Make Payment, but we do not forward to any 3rd party gateway

        Long flightId = request.getFlightId();
        Flight flight = flightRepository.findById(flightId).get();
        Passenger passenger = Passenger.builder()
                .firstName(request.getPassengerFirstName())
                .lastName(request.getPassengerLastName())
                .email(request.getPassengerEmail())
                .phone(request.getPassengerPhone())
                .build();

        Passenger savedPassenger = passengerRepository.save(passenger);
        Reservation reservation = Reservation.builder()
                .flight(flight)
                .passenger(savedPassenger)
                .checkedIn(false)
                .build();

        return reservationRepository.save(reservation);
    }
}
