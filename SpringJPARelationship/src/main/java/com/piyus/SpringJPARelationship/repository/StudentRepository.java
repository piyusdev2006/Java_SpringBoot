package com.piyus.SpringJPARelationship.repository;

import com.piyus.SpringJPARelationship.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {


    // wire up dependency
    @PersistenceContext
    private EntityManager entityManager;


    public void save(Student student) {
        entityManager.persist(student);
    }
}
