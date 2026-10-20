package com.piyus.SpringSecurityPractical.config;


import com.piyus.SpringSecurityPractical.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            CustomUserDetailsService  userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider provider
                = new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

//    @Bean
//    // this is used to authenticate using username and password via form login or Basic Auth
//    public SecurityFilterChain securityFilterChain(
//            HttpSecurity httpSecurity,
//            DaoAuthenticationProvider provider
//            ) throws Exception {
//
//            httpSecurity
//                    .csrf(csrf -> csrf.disable())
//                    .authenticationProvider(provider)
//                    .formLogin(Customizer.withDefaults())
//                    .httpBasic(Customizer.withDefaults())
//                    .authorizeHttpRequests(auth ->
//                            auth.requestMatchers("/api/users/register").permitAll()
//                                .anyRequest().authenticated()
//                    )
//                    // by default spring security kuch aisa perform krta hai
//                    .sessionManagement(
//                            sessionManagement -> sessionManagement
//                                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                    );
//            return httpSecurity.build();
//    }


    @Bean
    // this is used to authenticate using jwt token
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            DaoAuthenticationProvider provider,
            JwtAuthenticationConverter jwtAuthenticationConverter
    ) throws Exception {

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authenticationProvider(provider)

                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/users/register",
                                        "/auth/login").permitAll()
                                .anyRequest().authenticated()
                )
                // by default spring security kuch aisa perform krta hai
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .oauth2ResourceServer(
                        oauth2Resource ->
                                oauth2Resource.jwt(jwt ->
                                        jwt.jwtAuthenticationConverter(
                                                jwtAuthenticationConverter
                                        ))
                        );

        return httpSecurity.build();
    }

    @Bean
    public SecretKey jwtSecretKey(@Value("${jwt.secret}") String secret) {
        byte[] decodedkey = Base64.getDecoder().decode(secret);

        return new SecretKeySpec(
                decodedkey,
                "HmacSHA256"
        );
    }

    @Bean
    public AuthenticationManager authenticationManager(
            DaoAuthenticationProvider authenticationProvider) {

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtGrantedAuthoritiesConverter authoritiesConverter =
                new JwtGrantedAuthoritiesConverter();

        authoritiesConverter.setAuthoritiesClaimName(
                "authorities"
        );

        authoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter
                authenticationConverter =
                new JwtAuthenticationConverter();

        authenticationConverter
                .setJwtGrantedAuthoritiesConverter(
                        authoritiesConverter
                );

        return authenticationConverter;
    }


    @Bean
    // use to sign token
    public JwtEncoder jwtEncoder(
            SecretKey secretKey) {

        return NimbusJwtEncoder
                .withSecretKey(secretKey)
                .algorithm(MacAlgorithm.HS256)
                .build();
    }

    @Bean
    // used to verify the token
    public JwtDecoder jwtDecoder(
            SecretKey secretKey,
            @Value("${jwt.issuer}") String issuer) {

        NimbusJwtDecoder decoder =
                NimbusJwtDecoder
                        .withSecretKey(secretKey)
                        .macAlgorithm(MacAlgorithm.HS256)
                        .build();

        decoder.setJwtValidator(
                JwtValidators.createDefaultWithIssuer(
                        issuer
                )
        );

        return decoder;
    }

}
