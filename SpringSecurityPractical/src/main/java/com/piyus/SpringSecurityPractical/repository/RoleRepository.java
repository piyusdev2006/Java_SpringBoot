package com.piyus.SpringSecurityPractical.repository;

import com.piyus.SpringSecurityPractical.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository
        extends JpaRepository<Role, Long> {


    Optional<Role> findByName(String roleName);
}
