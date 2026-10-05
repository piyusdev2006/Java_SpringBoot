package com.hello1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Config {

    public void demo(){
        System.out.println("demo");
    }

//    @Bean(initMethod = "start", destroyMethod = "stop")
//    public CartService getCartService(){
//        return new CartService();
//    }
}
