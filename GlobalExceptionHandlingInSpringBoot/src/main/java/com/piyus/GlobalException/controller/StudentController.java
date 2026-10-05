package com.piyus.GlobalException.controller;

import com.piyus.GlobalException.dto.CreateStudentRequestDTO;
import com.piyus.GlobalException.dto.CreateStudentResponseDTO;
import com.piyus.GlobalException.dto.UpdateStudentRequestDTO;
import com.piyus.GlobalException.dto.UpdateStudentResponseDTO;
import com.piyus.GlobalException.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// DTO implemented

// validation : spring-boot-starter-validstion

@RestController
@RequestMapping("/api/students")  // common urlPath for all endpoints
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    // create Student : endpoints : POST --> /api/students/create
    @PostMapping
    public ResponseEntity<CreateStudentResponseDTO> createStudent(
            // applying the validation before
            @Valid
            @RequestBody CreateStudentRequestDTO studentRequestDTO){

        CreateStudentResponseDTO createdStudent = studentService.createStudent(studentRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    // read Student: endpoints : GET --> (/api/students/get{id}) for 1 record and {/api/students/getAll} for all records
    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDTO> getStudent(@PathVariable Long id){
        CreateStudentResponseDTO studentResponse = studentService.getStudent(id);

        return ResponseEntity
                .ok(studentResponse);

    }


    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDTO>> getAllStudent(){
        List<CreateStudentResponseDTO> studentList = studentService.getAllStudent();

        return ResponseEntity
                .ok(studentList);

    }

    // update Student : endpoints : PUT --> /api/students/update/{id} with {request body}
    @PutMapping
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(@RequestParam Long id, @RequestBody UpdateStudentRequestDTO updateStudentRequestDTO){
        UpdateStudentResponseDTO studentResponse = studentService.updateStudent(id, updateStudentRequestDTO);

        return ResponseEntity
                .ok(studentResponse);

    }


    // delete Student: endpoints : DELETE --> /api/students/delete/{id}
    @DeleteMapping
    public ResponseEntity<String> deleteStudent(@RequestParam Long id){
        studentService.deleteStudent(id);



        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

// softDelete Student: endpoints : PATCH --> /api/students/delete-soft/{id}
    @PatchMapping("/delete-soft")
    public ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id){studentService.deleteStudentSoftly(id);


        return ResponseEntity.noContent().build();
    }
}
