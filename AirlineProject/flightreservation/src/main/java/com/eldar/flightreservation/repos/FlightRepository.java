package com.eldar.flightreservation.repos;

import com.eldar.flightreservation.entities.Flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("FROM Flight " +
            "WHERE departureCity=:departureCity " +
            "AND arrivalCity=:arrivalCity " +
            "AND dateOfDeparture=:dateOfDeparture")
    List<Flight> findFlights(@Param("departureCity") String from,
                             @Param("arrivalCity") String to,
                             @Param("dateOfDeparture") Date departureDate);
}
