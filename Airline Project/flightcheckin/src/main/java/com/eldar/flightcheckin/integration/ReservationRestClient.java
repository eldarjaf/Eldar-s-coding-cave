package com.eldar.flightcheckin.integration;

import com.eldar.flightcheckin.integration.dto.Reservation;
import com.eldar.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {

	public Reservation findReservation(Long id);

	public Reservation updateReservation(ReservationUpdateRequest request);

}
