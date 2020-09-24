package com.eldar.flightreservation.controllers;

import com.eldar.flightreservation.config.FlightNotFoundException;
import com.eldar.flightreservation.entities.Flight;
import com.eldar.flightreservation.repos.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @RequestMapping(value = "/findFlights", method = RequestMethod.POST)
    public String findFlights(@RequestParam("from") String from,
                              @RequestParam("to") String to,
                              @RequestParam("departureDate")
                              @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate,
                              ModelMap modelMap) {
        log.debug("Inside findFlights() From: {}. TO: {}. Departure date: {}.", from, to, departureDate);
        List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
        if (flights.size() == 0) {
            throw new FlightNotFoundException("Flight not found. Please enter correct flights");
        }
        modelMap.addAttribute("flights", flights);
        log.debug("Flight found are: {}. ", flights);

        return "displayFlights";
    }

    @RequestMapping("admin/showAddFlight")
    public String showAddFlight() {

        return "addFlight";
    }

}
