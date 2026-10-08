package com.piyus.springBootAopPracticalDemo.service;

import com.piyus.springBootAopPracticalDemo.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public Student createStudent(Student student){
        System.out.println("Student saved..");

        // throw new RuntimeException("some error occured");

//        try {
//             throw new RuntimeException("some error occured");
//        }catch(RuntimeException e){
//
//        }

        return student;
    }


    public String dummyMethod(String s) {
        System.out.println("dummyMethod called");
        return s;
    }
}
