package com.piyus.OAuthInSpringBoot.config;

import com.piyus.OAuthInSpringBoot.service.CustomOIDCUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            CustomOIDCUserService customOidcUserService) {

        httpSecurity.authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("/")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
                .oauth2Login(oatuh ->
                        oatuh.userInfoEndpoint(userInfo ->
                                        userInfo.oidcUserService(customOidcUserService)
                                )
                                .defaultSuccessUrl("/profile", true
                                )
                );

        return httpSecurity.build();
    }}
