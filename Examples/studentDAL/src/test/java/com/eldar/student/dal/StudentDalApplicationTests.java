package com.eldar.student.dal;

import com.eldar.student.dal.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.eldar.student.dal.repos.StudentRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.Optional;

@SpringBootTest
class StudentDalApplicationTests {

    @Autowired
    public StudentRepository studentRepository;

    @Test
    public void testCreateStudent() {

        Student student = Student.builder()
                .name("ahmed")
                .course("an")
                .fee(20d)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void testFindStundentById() {
        Optional<Student> studentOptional = studentRepository.findById(1L);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            System.out.println(student);
        } else {
            System.out.println("Student not found");
        }
    }

    @Test
    public void testUpdateStudentById() {
        Optional<Student> studentOptional = studentRepository.findById(1L);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setName("zetram");

            studentRepository.save(student);
        } else {
            System.out.println("Student not found");
        }
    }

    @Test
    public void testDeleteStudentById() {
        Student student=Student.builder().id(2L).build();
        studentRepository.delete(student);
    }
}