package com.piyus.springBootAopPointCuts.service;

import com.piyus.springBootAopPointCuts.dto.Student;

public interface StudentServiceIterface {
    Student createStudent(Student student);
    String getStudent(String s);
}
