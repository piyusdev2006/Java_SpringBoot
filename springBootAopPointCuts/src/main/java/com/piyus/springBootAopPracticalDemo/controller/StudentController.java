package com.piyus.springBootAopPracticalDemo.controller;


import com.piyus.springBootAopPracticalDemo.dto.Student;
import com.piyus.springBootAopPracticalDemo.service.StudentService;
import com.piyus.springBootAopPracticalDemo.service.StudentServiceIterface;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {


    private StudentServiceIterface studentService;

    @Autowired
    public StudentController(StudentServiceIterface studentService){

        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student stu  = studentService.createStudent(student);
        return ResponseEntity.ok(stu);
    }


    @GetMapping
    public ResponseEntity<String> getStudent() {
        String s = "All Student Data";
        return ResponseEntity.ok(studentService.getStudent(s));
    }

//    @GetMapping("/greet")
//    public int greet() {
//        System.out.println("greet");
//        return 0;
//    }

}
