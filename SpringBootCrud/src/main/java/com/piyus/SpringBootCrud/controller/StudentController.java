package com.piyus.SpringBootCrud.controller;

import com.piyus.SpringBootCrud.dto.CreateStudentRequestDTO;
import com.piyus.SpringBootCrud.dto.CreateStudentResponseDTO;
import com.piyus.SpringBootCrud.dto.UpdateStudentRequestDTO;
import com.piyus.SpringBootCrud.dto.UpdateStudentResponseDTO;
import com.piyus.SpringBootCrud.entity.Student;
import com.piyus.SpringBootCrud.services.StudentService;
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
    @PostMapping("/create")
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
    @GetMapping("/get")
    public ResponseEntity<CreateStudentResponseDTO> getStudent(@RequestParam Long id){
        CreateStudentResponseDTO studentResponse = studentService.getStudent(id);

        if(studentResponse == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity
                .ok(studentResponse);

    }


    @GetMapping("/getAll")
    public ResponseEntity<List<CreateStudentResponseDTO>> getAllStudent(){
        List<CreateStudentResponseDTO> studentList = studentService.getAllStudent();

        if(studentList == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity
                .ok(studentList);

    }

    // update Student : endpoints : PUT --> /api/students/update/{id} with {request body}
    @PutMapping("/update")
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(@RequestParam Long id, @RequestBody UpdateStudentRequestDTO updateStudentRequestDTO){
        UpdateStudentResponseDTO studentResponse = studentService.updateStudent(id, updateStudentRequestDTO);

        if(studentResponse == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .ok(studentResponse);

    }


    // delete Student: endpoints : DELETE --> /api/students/delete/{id}
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam Long id){
        Boolean isDeleted = studentService.deleteStudent(id);

        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("record Deleted");
    }

// softDelete Student: endpoints : PATCH --> /api/students/delete-soft/{id}
    @PatchMapping("/delete-soft")
    public ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id){
        Boolean isDeleted = studentService.deleteStudentSoftly(id);

        if(!isDeleted){

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("record deleted softly");
    }
}
