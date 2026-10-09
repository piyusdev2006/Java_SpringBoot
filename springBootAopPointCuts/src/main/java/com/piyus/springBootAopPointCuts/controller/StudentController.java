package com.piyus.springBootAopPointCuts.controller;


import com.piyus.springBootAopPointCuts.dto.Student;
import com.piyus.springBootAopPointCuts.service.StudentServiceIterface;
import org.springframework.beans.factory.annotation.Autowired;
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
