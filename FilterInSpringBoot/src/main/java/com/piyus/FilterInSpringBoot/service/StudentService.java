package com.piyus.FilterInSpringBoot.service;

import com.piyus.FilterInSpringBoot.studentDto.StudentDTO;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public void createStudent(StudentDTO studentDTO){
        System.out.println("student created");
        System.out.println(studentDTO.getEmail());
        System.out.println(studentDTO.getName());


        try {
            Thread.sleep(2000);
        }
        catch (Exception e){

        }
    }
}
