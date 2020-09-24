package com.eldar.flightreservation.controllers;

import com.eldar.flightreservation.dto.ReservationUpdateRequest;
import com.eldar.flightreservation.entities.Reservation;
import com.eldar.flightreservation.repos.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class ReservationRestController {

    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/reservations/")
    public List<Reservation> findAllReservation() {
        return reservationRepository.findAll();
    }

    @GetMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id) {
        log.debug("Inside /reservations/{id}, {}.",id);
        return reservationRepository.findById(id).get();
    }

    @PostMapping("/reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
        log.debug("Inside updateReservation for request: {}. ",request);
        Reservation reservation = reservationRepository.findById(request.getId()).get();
        reservation.setNumberOfBags(request.getNumberOfBags());
        reservation.setCheckedIn(request.getCheckedIn());
        log.debug("Saving reservation");
        return reservationRepository.save(reservation);
    }

}
