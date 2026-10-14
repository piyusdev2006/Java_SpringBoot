package com.piyus.SpringJPARelationship2.controller;

import com.piyus.SpringJPARelationship2.model.Student;
import com.piyus.SpringJPARelationship2.service.StudentService;
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
            @RequestBody Student student) {

        studentService.createStudent(student);
        return ResponseEntity.ok("Student created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudents(@PathVariable Long id) {
       Student student = studentService.getStudentById(id);

       return ResponseEntity.ok(student);
    }
}
