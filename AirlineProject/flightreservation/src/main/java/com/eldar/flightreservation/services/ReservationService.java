package com.eldar.flightreservation.services;

import com.eldar.flightreservation.dto.ReservationRequest;
import com.eldar.flightreservation.entities.Reservation;

public interface ReservationService {
    Reservation bookFlight(ReservationRequest request);

}
