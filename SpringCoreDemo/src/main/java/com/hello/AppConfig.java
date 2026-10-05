package com.hello;

import org.example.CartService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.hello")
public class AppConfig {

    // complex constructor ko bhi handle
    // karke uske bhi object create kar pa rha hai
    @Bean
    public User createUser(){
        return new User(20, "Piyush");
    }

    // handling external third party library (jar files)
    // spring create object of it also
    @Bean
    public CartService Cs(){
        return new CartService();
    }
}
