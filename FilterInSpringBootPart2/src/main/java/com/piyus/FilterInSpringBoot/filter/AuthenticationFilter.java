package com.piyus.FilterInSpringBoot.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
public class AuthenticationFilter implements Filter {


    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = httpRequest.getHeader("token");

        if(token == null || !token.equals("12345")){

            // here we modify/alter the statuscode in resposeBody
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401 means unauthorized user

            httpResponse.setContentType("application/json");

            // herre we modify/alter the response body in resposeBody
            // humne direct filter se hi return kr diya responseBody ,
            // turnAround reponse controller se nhi return kiya body ko
            // hum aise kar bhi nhi sakte the
            httpResponse.getWriter().write("{\n" +
                    "  \"message\" : \"Authentication is required\";\n" +
                    "}\n");

            return;
        }



        chain.doFilter(request, response);

    }
}
