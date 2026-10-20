package com.piyus.OAuthInSpringBoot.repository;

import com.piyus.OAuthInSpringBoot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {


    Optional<User> findByProviderAndSubjectProvider(String provider, String subjectProvider);

}
