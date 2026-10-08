package com.piyus.springBootAopPracticalDemo.controller;


import com.piyus.springBootAopPracticalDemo.dto.Student;
import com.piyus.springBootAopPracticalDemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {


    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student stu  = studentService.createStudent(student);
        return ResponseEntity.ok(stu);
    }


    @GetMapping
    public ResponseEntity<String> dummyMethod() {
        String s = "Naveen";
        return ResponseEntity.ok(studentService.dummyMethod(s));
    }

}
