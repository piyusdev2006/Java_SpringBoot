package com.piyus.aopIntroduction.repository;


import com.piyus.aopIntroduction.dto.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    public void save(Student student) {
        System.out.println("Student saved");

    }
}