package com.piyus.SpringJPARelationship2.service;

import com.piyus.SpringJPARelationship2.model.Department;
import com.piyus.SpringJPARelationship2.model.Profile;
import com.piyus.SpringJPARelationship2.model.Student;
import com.piyus.SpringJPARelationship2.repository.DepartmentRepository;
import com.piyus.SpringJPARelationship2.repository.ProfileRepository;
import com.piyus.SpringJPARelationship2.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    StudentRepository studentRepository;
    DepartmentRepository departmentRepository;
    ProfileRepository profileRepository;


    public StudentService(
            StudentRepository studentRepository,
            DepartmentRepository departmentRepository,
            ProfileRepository profileRepository
    ) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.profileRepository = profileRepository;
    }


    @Transactional
    public void createStudent(Student student) {
        Department department = new Department();
        department.setName("CSE");

        Profile profile = new Profile();
        profile.setBio("Developer");

        student.setDepartment(department);
        student.setProfile(profile);

        departmentRepository.save(department);
        profileRepository.save(profile);
        studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
       Student s1 = studentRepository.findById(id);

        System.out.println("Lazily fetching student with id " + id);

        Department department = s1.getDepartment();
        System.out.println("Lazily fetching details");

        Profile profile = s1.getProfile();
        System.out.println("Lazily fetching profile");

        return s1;
    }
}
