package com.eldar.flightreservation.repos;

import com.eldar.flightreservation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}

//as "email" is the field of the User object, email is the column of User entity
//you do not need to implement it, it will work like findById
