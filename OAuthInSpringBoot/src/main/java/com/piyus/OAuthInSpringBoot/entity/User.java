package com.piyus.OAuthInSpringBoot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String provider;

    private String subjectProvider;

    public User(
            String name,
            String email,
            String provider,
            String subjectProvider
    ) {
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.subjectProvider = subjectProvider;
    }

}
