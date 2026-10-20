package com.piyus.SpringSecurityPractical.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestDTO {

    private String username;

    private String password;
}
