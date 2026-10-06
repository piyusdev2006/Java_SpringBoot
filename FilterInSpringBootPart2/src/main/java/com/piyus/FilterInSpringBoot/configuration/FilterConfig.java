package com.piyus.FilterInSpringBoot.configuration;


import com.piyus.FilterInSpringBoot.filter.DummyFIlter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.print.DocFlavor;

@Configuration
public class FilterConfig {

//    special bean  using class : FilterRegistrationBean
    @Bean
    public FilterRegistrationBean<DummyFIlter> getDummyFilterBean(){
        FilterRegistrationBean<DummyFIlter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new DummyFIlter());

        filterRegistrationBean.setOrder(1);

        filterRegistrationBean.addUrlPatterns("/api/*, /admin/*");
//        filterRegistrationBean.setName("");

        return filterRegistrationBean;
    }
}
