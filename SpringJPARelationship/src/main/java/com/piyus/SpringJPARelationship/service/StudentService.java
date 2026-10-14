package com.piyus.SpringJPARelationship.service;

import com.piyus.SpringJPARelationship.model.Department;
import com.piyus.SpringJPARelationship.model.Student;
import com.piyus.SpringJPARelationship.repository.DepartmentRepository;
import com.piyus.SpringJPARelationship.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    StudentRepository studentRepository;

    // WE need department object so that we store department in the student table
    DepartmentRepository departmentRepository;

    public StudentService(
            StudentRepository studentRepository,
            DepartmentRepository departmentRepository
    ) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }


    @Transactional
    public void createStudent(Student student, Long deptId) {

        Department department =
                departmentRepository.getDepartmentById(deptId);


        student.setDepartment(department);
        department.getStudents().add(student);

        studentRepository.save(student);

    }

    @Transactional
    public void createStudent(Student student, String deptName) {

        // first creating new department
        Department department = new Department();

        // setting department name
        department.setName(deptName);
        student.setDepartment(department);

        department.getStudents().add(student);
        departmentRepository.save(department);
        studentRepository.save(student);

    }
}
