package com.piyus.SpringSecurityPractical.service;

import com.piyus.SpringSecurityPractical.entity.CustomUserDetails;
import com.piyus.SpringSecurityPractical.entity.User;
import com.piyus.SpringSecurityPractical.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService
        implements UserDetailsService
{

    private UserRepository userRepository;
    public CustomUserDetailsService
            (
                    UserRepository userRepository
            )
    {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername
            (
                    String username
            )
            throws UsernameNotFoundException
    {

        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("user not found"));

        // returning user by wrapping in a UserDetails concrete  lass object
        return new CustomUserDetails(user);
    }
}
