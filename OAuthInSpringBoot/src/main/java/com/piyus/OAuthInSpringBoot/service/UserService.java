package com.piyus.OAuthInSpringBoot.service;

import com.piyus.OAuthInSpringBoot.entity.User;
import com.piyus.OAuthInSpringBoot.repository.UserRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerOrUpdateUser(String provider, OidcUser oidcUser) {
        String providerSubject = oidcUser.getSubject();

        String name = oidcUser.getName();
        String email = oidcUser.getEmail();

        Optional<User> existingUser
                = userRepository
                .findByProviderAndSubjectProvider(provider, providerSubject);

        if (existingUser.isPresent()) {

            User user = existingUser.get();

            user.setName(name);
            user.setEmail(email);

            return user;
        }

        User newuser = new User(name, email, provider, providerSubject);

        return userRepository.save(newuser);
    }


    public Optional<User> findByProviderAndSubject(
            String provider,
            String subject) {

        return userRepository.findByProviderAndSubjectProvider(
                provider, subject);
    }
}
