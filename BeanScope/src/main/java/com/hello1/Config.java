package com.hello1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.hello1")
public class Config {


    // ab yaha 2 beans create kiye gye hai aur sye singleton hai ,
    // par alag alag beans hai aur khud me singleton hai

//    @Bean
//    public OrderService getOrder(){
//        return new OrderService();
//    }
//
//    @Bean
//    public OrderService getOrder2(){
//        return new OrderService();
//    }
}
