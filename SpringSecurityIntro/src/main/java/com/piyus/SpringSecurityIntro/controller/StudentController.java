package com.piyus.SpringSecurityIntro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @GetMapping
    public ResponseEntity<String> getStudents() {
        return ResponseEntity
                .ok("Details of Students fetched Successfully");
    }

    @PostMapping
    public ResponseEntity<String> createStudents() {
        return ResponseEntity
                .ok("Students created Successfully");
    }


   @DeleteMapping
    public ResponseEntity<String> deleteStudents() {
        return ResponseEntity
                .ok("Students deleted Successfully");
    }

    @PutMapping
    public ResponseEntity<String> updateStudents() {
        return ResponseEntity
                .ok("Students update Successfully");
    }


    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(CsrfToken csrfToken) {
        return csrfToken;
    }
}
