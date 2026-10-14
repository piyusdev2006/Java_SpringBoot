package com.piyus.SpringJPARelationship2.repository;

import com.piyus.SpringJPARelationship2.model.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public void save(Department department) {
        entityManager.persist(department);
    }

    public void removeDepartment(Department id) {
        entityManager.remove(id);
    }

    public Department findById(long id) {
        return entityManager.find(Department.class, id);
    }
}
