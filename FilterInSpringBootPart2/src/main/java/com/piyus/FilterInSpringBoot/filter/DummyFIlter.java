package com.piyus.FilterInSpringBoot.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class DummyFIlter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)request;

        String uri = httpServletRequest.getRequestURI();

        if(!uri.startsWith("/api/*")){
            chain.doFilter(request, response);
        }

        System.out.println("dummy filter called");
        chain.doFilter(request, response);
    }
}
