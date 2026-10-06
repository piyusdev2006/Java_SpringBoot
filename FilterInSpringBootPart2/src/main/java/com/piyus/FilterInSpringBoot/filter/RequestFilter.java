package com.piyus.FilterInSpringBoot.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

//@Component
public class RequestFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // here we can get token value from header

      // String token = httpRequest.getHeader("token");

        // can we change incoming header ->
        // ans: jo bhi header, Request Parameter->
        // HttpServletRequest build krta hai wo sirf read-only hote hai



        BufferedReader reader =
                httpRequest.getReader();

        StringBuilder body = new StringBuilder();

        String line = reader.readLine();

        while(line != null) {
            body.append(line);
            line = reader.readLine();
        }

        System.out.println(body);

        chain.doFilter(request, response);
    }
}


