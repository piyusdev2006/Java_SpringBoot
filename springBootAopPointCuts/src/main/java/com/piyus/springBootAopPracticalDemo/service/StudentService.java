package com.piyus.springBootAopPracticalDemo.service;

import com.piyus.springBootAopPracticalDemo.dto.Student;
import jdk.jfr.Timestamp;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements StudentServiceIterface{

    // @Timestamp
    @Override
    public Student createStudent(Student student){
        System.out.println("Student saved..");

        return student;
    }


    // custom annotation
//    @Audit
//    @Evaluate
    @Override
    public String getStudent(String s) {

        System.out.println(s);
        return s;
    }

//    public int dummymethod(){
//        return 0;
//    }
}
