package com.piyus.sb_customAnnotationAndProxies.service;

import com.piyus.sb_customAnnotationAndProxies.annotation.TrackExecutionTime;
import com.piyus.sb_customAnnotationAndProxies.dto.Student;
import jdk.jfr.Timestamp;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @TrackExecutionTime(
            warnAfter = 2000,
            operation = "Creating New Student"
    )
    public Student createStudent(Student student){

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e){}

        System.out.println("Student saved..");

        return student;
    }


    // @Timestamp
    @TrackExecutionTime( 
            warnAfter = 1500,
            operation = "Get Student data"
    )
    public String getStudent(String s) {

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e){}


        System.out.println(s);
        return s;
    }


    public int dummyMethod() {
        return 0;
    }
}
