package com.eldar.flightreservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationUpdateRequest {
    private Long id;
    private Boolean checkedIn;
    private int numberOfBags;
}
