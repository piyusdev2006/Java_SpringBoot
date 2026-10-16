package com.piyus.SpringDataJPASpringBoot.repository;

import com.piyus.SpringDataJPASpringBoot.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    @Query(value = """
                select * from student
                where email = :email
                """, nativeQuery = true)
    Optional<Student> findByEmail(@Param("email") String email);

}
