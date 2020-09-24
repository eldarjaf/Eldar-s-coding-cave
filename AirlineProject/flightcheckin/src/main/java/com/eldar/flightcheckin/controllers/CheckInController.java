package com.eldar.flightcheckin.controllers;

import com.eldar.flightcheckin.integration.ReservationRestClient;
import com.eldar.flightcheckin.integration.dto.Reservation;
import com.eldar.flightcheckin.integration.dto.ReservationUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckInController {

    @Autowired
    ReservationRestClient restClient;

    @RequestMapping("/showStartCheckin")
    public String showStartCheckin() {
        return "startCheckIn";
    }

    @RequestMapping("/startCheckIn")
    public String startCheckIn(@RequestParam("reservationId") Long reservationId, ModelMap modelMap) {
        Reservation reservation = restClient.findReservation(reservationId);
        modelMap.addAttribute("reservation", reservation);
        return "displayReservationDetails";

    }

    @RequestMapping("/completeCheckIn")
    public String completeCheckIn(@RequestParam("reservationId") Long reservationId,
                                  @RequestParam("numberOfBags") int numberOfBags,
                                  ModelMap modelMap) {

        ReservationUpdateRequest request = ReservationUpdateRequest.builder()
                .checkedIn(true)
                .id(reservationId)
                .numberOfBags(numberOfBags)
                .build();

        restClient.updateReservation(request);
        modelMap.addAttribute("msg", "Checkin completed successully");
        return "checkInConfirmation";

    }

}
