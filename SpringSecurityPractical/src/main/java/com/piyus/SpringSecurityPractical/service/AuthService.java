package com.piyus.SpringSecurityPractical.service;

import com.piyus.SpringSecurityPractical.dto.UserRegisterRequestDTO;
import com.piyus.SpringSecurityPractical.dto.UserRegisterResponseDTO;
import com.piyus.SpringSecurityPractical.entity.Role;
import com.piyus.SpringSecurityPractical.entity.User;
import com.piyus.SpringSecurityPractical.repository.RoleRepository;
import com.piyus.SpringSecurityPractical.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
            , RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserRegisterResponseDTO register(
            UserRegisterRequestDTO
                    userRegisterRequestDTO
    ){
        User user = new User();
        user.setUsername(userRegisterRequestDTO.getUsername());

        String encodedPassword = passwordEncoder.encode(userRegisterRequestDTO.getPassword());
        user.setPassword(encodedPassword);

        user.setEnabled(true);

        Role role = roleRepository.findByName("ROLE_USER").get();

        user.getRoles().add(role);
        userRepository.save(user);

        UserRegisterResponseDTO responseDTO = new UserRegisterResponseDTO();

        responseDTO.setUsername(user.getUsername());
        responseDTO.setMessage("User Registered Successfully");

        return responseDTO;
    }

}
