package com.piyus.FilterInSpringBoot.service;

import com.piyus.FilterInSpringBoot.studentDto.StudentDTO;
import com.piyus.FilterInSpringBoot.studentDto.StudentResponseDto;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public StudentResponseDto createStudent(StudentDTO studentDTO) {
        StudentResponseDto responseDto = new StudentResponseDto();
        responseDto.setName(studentDTO.getName());
        responseDto.setMessage("Student is saved succesfully");

        return responseDto;
    }
}