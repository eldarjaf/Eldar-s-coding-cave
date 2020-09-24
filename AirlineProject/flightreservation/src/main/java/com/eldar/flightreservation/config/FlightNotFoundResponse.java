package com.eldar.flightreservation.config;

public class FlightNotFoundResponse {
    private final String errorMessage;

    public FlightNotFoundResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getError(){
        return "Flight not found error";
    }

    public String getMessage(){
        return errorMessage;
    }
}
