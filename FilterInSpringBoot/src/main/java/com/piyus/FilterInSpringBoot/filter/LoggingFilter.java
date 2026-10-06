package com.piyus.FilterInSpringBoot.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(2)
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException
    {
        // controller isliye banaya kyoki hum request ko Log krna chahta hu
        // apne filter ka use karke

        // Here we need to typecast this to get the HttpServletRequest
        // here we logging the request

        // request turnaround time
        long startTime = System.currentTimeMillis();

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String reqId = UUID.randomUUID().toString();
        httpResponse.setHeader("X-Request-ID", reqId);


        System.out.println("Incoming Request"
                + httpRequest.getMethod()
                + " " +
                httpRequest.getRequestURI()
        );

       try {
           chain.doFilter(request, response);
       }
       finally {
           long duration = System.currentTimeMillis() - startTime;

           // Response Status log
           System.out.println("Response Status: " +
                   httpResponse.getStatus()
           );
           System.out.println("API Response Time: " + duration);
       }

    }
}
