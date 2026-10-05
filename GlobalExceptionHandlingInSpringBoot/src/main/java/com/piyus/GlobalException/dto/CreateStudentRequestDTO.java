package com.piyus.GlobalException.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDTO {

    @NotBlank(message = "name cannot be < 2 character")
    @Size(min = 3, max = 18, message = "name must be between 2-50 character")
    private String name;

    @NotBlank(message = "email cannot be blank")
    @Email(message = "email must be valid")
    private String email;

    @NotNull(message = "age is required")
    @Min(value = 18, message = "user must be 18 year old")
    private int age;

    @NotNull(message = "reuired")
    private Integer rollNo;

    @NotBlank(message = "subject is required")
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
