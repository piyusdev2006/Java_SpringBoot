package com.piyus.SpringJPARelationship2.repository;

import com.piyus.SpringJPARelationship2.model.Department;
import com.piyus.SpringJPARelationship2.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {


    // wire up dependency
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Student student) {
        entityManager.persist(student);
    }

    @EntityGraph(attributePaths = "profile")
    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

//    @EntityGraph(attributePaths = {"department", "profile"})
//    public List<Student> findAll() {
//    }

}
