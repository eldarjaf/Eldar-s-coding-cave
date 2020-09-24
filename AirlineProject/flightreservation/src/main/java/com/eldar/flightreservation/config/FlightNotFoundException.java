package com.eldar.flightreservation.config;

public class FlightNotFoundException extends RuntimeException {

    public FlightNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlightNotFoundException(String message) {
        super(message);
    }
}
