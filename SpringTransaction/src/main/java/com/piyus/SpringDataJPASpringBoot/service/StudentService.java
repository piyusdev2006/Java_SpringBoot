package com.piyus.SpringDataJPASpringBoot.service;

import com.piyus.SpringDataJPASpringBoot.model.Student;
import com.piyus.SpringDataJPASpringBoot.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student) {
        studentRepository.save(student);
    }


    public Student getStudentById(Long id) {
        Optional<Student> student =  studentRepository.findById(id);

        return student.orElse(null);
    }

    // sorting and  // pagination

    public List<Student> getAllStudents(String name) {

        Sort sort = Sort.by("age");

        Pageable pageable = PageRequest.of(0, 2);

        Page<Student> students =
                studentRepository.findAll(pageable);
        for(Student s : students.getContent()) {
            System.out.println(s);
        }

        return students.getContent();
    }

    public void updateStudent(Student studentReq, Long id) {
        Optional<Student>studentRes = studentRepository.findById(id);

        Student student1 = studentRes.get();

        student1.setName(studentReq.getName());
        student1.setAge(studentReq.getAge());
    }



}

