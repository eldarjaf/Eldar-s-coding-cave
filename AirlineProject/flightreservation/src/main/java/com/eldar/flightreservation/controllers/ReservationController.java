package com.eldar.flightreservation.controllers;

import com.eldar.flightreservation.dto.ReservationRequest;
import com.eldar.flightreservation.entities.Flight;
import com.eldar.flightreservation.entities.Reservation;
import com.eldar.flightreservation.repos.FlightRepository;
import com.eldar.flightreservation.services.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Slf4j
@Controller
public class ReservationController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationService reservationService;

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long id, ModelMap modelMap) {
        log.debug("Inside showCompleteReservation(), flight id: {}.", id);
        Optional<Flight> flight = flightRepository.findById(id);
        modelMap.addAttribute("flight", flight.get());
        log.debug("Flight is : " + flight);
        return "completeReservation";
    }

    @PostMapping("/completeReservation")
    public String completeReservation(ReservationRequest request, ModelMap modelMap) {
        log.debug("completeReservation() request: " + request);
        Reservation reservation = reservationService.bookFlight(request);
        modelMap.addAttribute("msg", "Reservation created successfully and ID is " + reservation.getId());

        return "reservationConfirmation";
    }

}
