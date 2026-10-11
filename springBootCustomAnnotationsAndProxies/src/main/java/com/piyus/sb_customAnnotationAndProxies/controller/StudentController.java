package com.piyus.sb_customAnnotationAndProxies.controller;


import com.piyus.sb_customAnnotationAndProxies.dto.Student;
import com.piyus.sb_customAnnotationAndProxies.service.StudentService;
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
    public ResponseEntity<String> getStudent() {
        String s = "All Student Data";
        return ResponseEntity.ok(studentService.getStudent(s));
    }

}
