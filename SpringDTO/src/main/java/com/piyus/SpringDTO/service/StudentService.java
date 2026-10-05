package com.piyus.SpringDTO.service;

import com.piyus.SpringDTO.controller.StudentController;
import com.piyus.SpringDTO.entity.Student;
import com.piyus.SpringDTO.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq){
        return studentRepository.save(studentReq);
    }


}
