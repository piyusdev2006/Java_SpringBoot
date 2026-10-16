package com.piyus.SpringDataJPASpringBoot.controller;

import com.piyus.SpringDataJPASpringBoot.model.Student;
import com.piyus.SpringDataJPASpringBoot.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("name/{name}")
    public ResponseEntity<List<Student>> getAllStudents(@PathVariable String name) {
        List<Student> studentList = studentService.getAllStudents(name);

        return ResponseEntity.ok(studentList);
    }
}
