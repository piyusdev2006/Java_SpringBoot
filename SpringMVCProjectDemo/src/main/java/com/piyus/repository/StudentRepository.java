package com.piyus.repository;

import com.piyus.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    private Map<Long, Student> studentMap;

    public StudentRepository(){
        studentMap = new HashMap<>();
    }

    public Student saveToDb(Student studentReq){
        studentMap.put(studentReq.getId(), studentReq);
        return studentReq;
    }

    public Student findById(Long id){
        return studentMap.get(id);
    }

    public List<Student> findAll(){
        return new ArrayList<>(studentMap.values());
    }

}
