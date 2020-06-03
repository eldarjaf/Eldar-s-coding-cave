package com.eldar.flightreservation.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Builder(toBuilder = true)
@Table(name = "PASSENGER")
public class Passenger extends AbstractEntity{
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;
}
