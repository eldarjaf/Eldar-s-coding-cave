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
public class Passenger {

	private Long id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String email;
	private String phone;
}
