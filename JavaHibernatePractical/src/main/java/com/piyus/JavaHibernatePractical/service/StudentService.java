package com.piyus.JavaHibernatePractical.service;

import com.piyus.JavaHibernatePractical.model.Student;
import com.piyus.JavaHibernatePractical.repository.StudentRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return null;
    }


    @Transactional
    public Student getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public void updateStudent(Student studentReq, Long id) {
        Student student1 = studentRepository.findById(id);

        if(student1 == null){
            throw new RuntimeException("Student not found");
        }

        student1.setName(studentReq.getName());
        student1.setEmail(studentReq.getEmail());
        student1.setAge(studentReq.getAge());
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student1 = studentRepository.findById(id);

        if(student1 == null){
            throw new RuntimeException("Student not found");
        }

        studentRepository.remove(student1);
    }
}
