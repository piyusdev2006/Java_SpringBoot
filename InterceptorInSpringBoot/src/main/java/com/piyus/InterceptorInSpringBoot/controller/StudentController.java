package com.piyus.InterceptorInSpringBoot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    // POST /api/students
    @PostMapping
    public ResponseEntity<String> createStudent() {

        System.out.println("POST - controller called");

        return ResponseEntity.ok("Student Created");
    }

    // GET /api/students
    @GetMapping
    public ResponseEntity<String> getAllStudents() {

        System.out.println("GET ALL - controller called");

        return ResponseEntity.ok("All Students");
    }

    // GET /api/students/10
    @GetMapping("/{id}")
    public ResponseEntity<String> getStudentById(
            @PathVariable Long id
    ) {

        System.out.println("GET BY ID - controller called");

        return ResponseEntity.ok("Student with ID: " + id);
    }

    // PUT /api/students/10
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(
            @PathVariable Long id
    ) {

        System.out.println("PUT - controller called");

        return ResponseEntity.ok("Student " + id + " Updated");
    }

    // DELETE /api/students/10
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id
    ) {

        System.out.println("DELETE - controller called");

        return ResponseEntity.ok("Student " + id + " Deleted");
    }

    // PATCH /api/students/10
    @PatchMapping("/{id}")
    public ResponseEntity<String> partialUpdateStudent(
            @PathVariable Long id
    ) {

        System.out.println("PATCH - controller called");

        return ResponseEntity.ok("Student " + id + " Partially Updated");
    }

}
