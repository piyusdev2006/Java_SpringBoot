package com.piyus.SpringSecurityPractical.controller;

import com.piyus.SpringSecurityPractical.dto.UserRegisterRequestDTO;
import com.piyus.SpringSecurityPractical.dto.UserRegisterResponseDTO;
import com.piyus.SpringSecurityPractical.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/hello")
    public String sayHello(Authentication authentication) {

        return "Hello, You are logged in as " + authentication.getName();
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO>register(
            @RequestBody UserRegisterRequestDTO userRegisterRequestDTO
    ){
        UserRegisterResponseDTO userRegisterResponseDTO
                = authService.register(userRegisterRequestDTO);
        return ResponseEntity.ok(userRegisterResponseDTO);
    }

    // Not needed anymore
    //@GetMapping("/token")
    //public CsrfToken getCsrfToken(CsrfToken csrfToken){
        //return csrfToken;
    //}

}
