package com.piyus.controller;

import com.piyus.entity.Student;
import com.piyus.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student>createStudent(@RequestBody Student studentReq){
        Student studentResponse = studentService.createStudent(studentReq);

        return ResponseEntity.ok(studentReq);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long id){
        Student res = studentService.getStudent(id);

        if(res == null){
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(res);
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> res = studentService.getAllStudent();

        if(res == null){
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(res);
    }

}
