package com.eldar.flightcheckin.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReservationUpdateRequest {
	
	private Long id;
	private Boolean checkedIn;
	private int numberOfBags;
}
