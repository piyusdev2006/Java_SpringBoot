package com.piyus.SpringBootCrud.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDTO {

    @NotBlank(message = "name cannot be < 2 character")
    @Size(min = 3, max = 18)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Min(value = 12)
    private int age;

    @NotNull
    private Integer rollNo;

    @NotBlank
    private String subject;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
