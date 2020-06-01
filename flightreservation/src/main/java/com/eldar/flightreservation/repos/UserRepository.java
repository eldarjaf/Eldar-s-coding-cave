package com.eldar.flightreservation.repos;

import com.eldar.flightreservation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}

//as "email" is the field of the User object, email is the column of User entity
//you do not need to implement it, it will work like findById
