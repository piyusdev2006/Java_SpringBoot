package com.piyus.SpringJPARelationship.controller;

import com.piyus.SpringJPARelationship.model.Student;
import com.piyus.SpringJPARelationship.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping()
    public ResponseEntity<String> createStudent(
            @RequestBody Student student,
            @RequestParam Long deptId) {

        studentService.createStudent(student, deptId);
        return ResponseEntity.ok("Student created successfully");
    }


    // assigning dynamic department to students
    @PostMapping("/withDept")
    public ResponseEntity<String> createStudent(
            @RequestBody Student student,
            @RequestParam String deptName) {

        studentService.createStudent(student, deptName);
        return ResponseEntity.ok("Student created successfully");
    }



}
