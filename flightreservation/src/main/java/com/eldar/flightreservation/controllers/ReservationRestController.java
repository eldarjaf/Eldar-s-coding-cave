package com.eldar.flightreservation.controllers;

import com.eldar.flightreservation.dto.ReservationUpdateRequest;
import com.eldar.flightreservation.entities.Reservation;
import com.eldar.flightreservation.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationRestController {

    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id) {
        return reservationRepository.findById(id).get();
    }

    @GetMapping("/reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
        Reservation reservation = reservationRepository.findById(request.getId()).get();
        reservation.setNumberOfBags(request.getNumberOfBags());
        reservation.setCheckedIn(request.getCheckedIn());
        return reservationRepository.save(reservation);
    }

}
