package com.piyus.SpringSecurityPractical.service;


import com.piyus.SpringSecurityPractical.entity.Role;
import com.piyus.SpringSecurityPractical.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addRole(Role role){
        roleRepository.save(role);
    }
}
