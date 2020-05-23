package com.eldar.student.dal.repos;

import com.eldar.student.dal.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface StudentRepository extends CrudRepository<Student,Long> {


}
