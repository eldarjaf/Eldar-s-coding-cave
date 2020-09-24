package com.eldar.flightreservation.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<FlightNotFoundResponse> flightNotFound (FlightNotFoundException fnfe){
        FlightNotFoundResponse response= new FlightNotFoundResponse(fnfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
