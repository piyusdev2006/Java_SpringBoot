package com.piyus.FilterInSpringBoot.controller;

import com.piyus.FilterInSpringBoot.service.StudentService;
import com.piyus.FilterInSpringBoot.studentDto.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    StudentService studentService;
    public StudentController(StudentService studentService){

        this.studentService = studentService;

    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody StudentDTO student){
        studentService.createStudent(student);

        return ResponseEntity.ok("DONE");
    }
}
