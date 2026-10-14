package com.piyus.JavaHibernateInternals.repository;

import com.piyus.JavaHibernateInternals.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

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

//        entityManager.contains(s1);

//        return s1;
    }

    public void detach(Student student){
        entityManager.detach(student);
    }

    public Student attachAgain(Student student){
        return entityManager.merge(student);
    }

    //delete
    public void remove(Student student) {
        entityManager.remove(student);
    }


    // flush use case in batch processing :
    // best for both persistence context and database
    public void saveAll(List<Student> students) {
        int count = 0;

        for (Student student : students) {
            entityManager.persist(student);
            count++;

            if (count % 100 == 0) {
                entityManager.flush();
                entityManager.clear();

                // entityManager.refresh(student);
            }
        }
    }

    //update - hibernate has no such methods of update
}
