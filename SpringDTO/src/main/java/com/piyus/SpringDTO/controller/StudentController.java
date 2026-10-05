package com.piyus.SpringDTO.controller;


import com.piyus.SpringDTO.entity.Student;
import com.piyus.SpringDTO.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class StudentController{

    StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    // create
    public ResponseEntity<Student> create(@RequestBody Student student){
        Student res=  studentService.createStudent(student);

        return ResponseEntity.ok(res);
    }

    // read


    // update

    // delete
}
