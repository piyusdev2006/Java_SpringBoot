package com.piyus.SpringBootCrud.repository;

import com.piyus.SpringBootCrud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    Optional<Student> findByIdAndDeletedIsFalse(Long id);
    List<Student> findByDeletedIsFalse();
}







/*
    public Student saveStudent(Student studentReq){
        // save to db logic

         return null;

          Saving dummy data
            Student s1  = new Student();
            s1.setName("Naveen");
            s1.setAge(20);
            s1.setEmail("singhpiyus@gmail.com");
            s1.setRollNo(101);
            s1.setSubject("spring framework");
            return s1;


    }
  */