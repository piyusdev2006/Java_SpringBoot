package com.piyus.SpringJPARelationship2.service;

import com.piyus.SpringJPARelationship2.model.Department;
import com.piyus.SpringJPARelationship2.model.Student;
import com.piyus.SpringJPARelationship2.repository.DepartmentRepository;
import com.piyus.SpringJPARelationship2.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Student s1 = new Student();
        s1.setName("Naveen Singh");
        s1.setDepartment(department);

        Student s2 = new Student();
        s2.setName("Naveen Singh");
        s2.setDepartment(department);

        Student s3 = new Student();
        s3.setName("Naveen Singh");
        s3.setDepartment(department);

        Student s4 = new Student();
        s4.setName("Naveen Singh");
        s4.setDepartment(department);

        //department.getStudents().addAll(List.of(s1, s2, s3, s4));

        departmentRepository.save(department);

        //studentRepository.save(s1);
        //studentRepository.save(s2);
        //studentRepository.save(s3);
        //studentRepository.save(s4);
    }

    @Transactional
    public void removeDepartment(Long id) {
        Department department = departmentRepository.findById(id);

        departmentRepository.removeDepartment(department);
    }

}
