package com.piyus.JavaHibernatePractical.repository;

import com.piyus.JavaHibernatePractical.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {


    // wire up dependency
    @PersistenceContext
    private EntityManager entityManager;


    //create
    public void save(Student student) {
        entityManager.persist(student);
    }

    // read
    public  Student findById(Long id){
        return entityManager.find(Student.class,id);
    }

    //delete
    public void remove(Student student){
        entityManager.remove(student);
    }

    //update - hibernate has no such methods of update
}
