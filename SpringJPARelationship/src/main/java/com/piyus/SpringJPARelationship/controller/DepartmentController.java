package com.piyus.SpringJPARelationship.controller;

import com.piyus.SpringJPARelationship.model.Department;
import com.piyus.SpringJPARelationship.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<String> createDepartment(
            @RequestBody Department department) {
        departmentService.createDepartment(department);
        return ResponseEntity.ok("Department Created");
    }


    @PostMapping("/withStudent")
    public ResponseEntity<String> createDepartment(
            @RequestBody Department department,
            @RequestParam String studentName
    ) {
        departmentService.createDepartment(department, studentName);
        return ResponseEntity.ok("Department Created");
    }

}
