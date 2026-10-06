package com.piyus.FilterInSpringBoot.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

//@Component
public class ResponseHeaderFilter implements Filter {


    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException
    {
        // Note: when we work with HTTP request/reponse
        // we can't work with ServletRequest request,
        // ServletResponse response, they are generic req/res

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestId = UUID.randomUUID().toString();

        // hum responseHeader pahle hi isliye modify kar rhe
        // isko doFilter() ke baad kar sakte the , lekin
        // jo response humein turnaround hoke milta hai usme reponse ko DispatcherServlet likhta hai
        // jo ki stream response hoti hai, means wo commit ho chuki hoti hai aur aur
        // wo read-only ban jaati hai , jisme filetr kuch bhi nhi lokh sakta hai

        // here we modify header in responseBody
        httpServletResponse.setHeader("x-request-id", requestId);


        // iske pahle header hum set kar sakte hai means modify kar sakte hai
        chain.doFilter(request, response);


        // yaha karne se koi response me modification nhi milega
        // httpServletResponse.setHeader("x-request-id", requestId);

    }


}
