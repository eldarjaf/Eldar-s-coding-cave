package com.eldar.flightreservation.repos;

import com.eldar.flightreservation.entities.Flight;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Long> {

}
