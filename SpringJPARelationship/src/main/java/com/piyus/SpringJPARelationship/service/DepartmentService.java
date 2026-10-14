package com.piyus.SpringJPARelationship.service;

import com.piyus.SpringJPARelationship.model.Department;
import com.piyus.SpringJPARelationship.model.Student;
import com.piyus.SpringJPARelationship.repository.DepartmentRepository;
import com.piyus.SpringJPARelationship.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;
    private StudentRepository studentRepository;


    public DepartmentService(
            DepartmentRepository departmentRepository,
            StudentRepository studentRepository
    ) {
        this.departmentRepository = departmentRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void createDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Transactional
    public void createDepartment(
            Department department,
            String studentName) {

        Student student = new Student();
        student.setName(studentName);

        student.setDepartment(department);

        department.getStudents().add(student);

        departmentRepository.save(department);
        studentRepository.save(student);
    }
}
