package com.piyus.SpringDTO.repository;

import com.piyus.SpringDTO.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long>{
}
