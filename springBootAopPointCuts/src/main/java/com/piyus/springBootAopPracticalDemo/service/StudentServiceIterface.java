package com.piyus.springBootAopPracticalDemo.service;

import com.piyus.springBootAopPracticalDemo.dto.Student;

public interface StudentServiceIterface {
    Student createStudent(Student student);
    String getStudent(String s);
}
