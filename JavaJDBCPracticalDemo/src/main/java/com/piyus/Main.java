package com.piyus;

import com.piyus.model.Student;
import com.piyus.repository.StudnetRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    static void main() {

        System.out.println("Hello world");


        StudnetRepository studnetRepository = new StudnetRepository();

//        studnetRepository.createStudent(new Student(
//                "Naveen Singh",
//                "singhnavin1@gmail.com",
//                29));

//        studnetRepository.updateStudent(new Student(
//                "Chotu Singh",
//                "chotusingh123@gmail.com",
//                  26) ,
//                   6L);


//        studnetRepository.deleteStudent(6L);

//        studnetRepository.getStudentById(5L);

        studnetRepository.getStudent();


    }
}

