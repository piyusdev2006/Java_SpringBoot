package com.piyus.InterceptorInSpringBoot.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        System.out.println("Authorization checking...");

        // AuthenticationInterceptor ne role store kiya tha
        String role = (String) request.getAttribute("role");

        System.out.println("Role: " + role);

        // Role nahi mila
        if (role == null) {

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );

            response.setContentType("application/json");

            response.getWriter().write("""
                    {
                        "success": false,
                        "message": "User is not authenticated"
                    }
                    """);

            return false;
        }

        String httpMethod = request.getMethod();

        System.out.println("HTTP Method: " + httpMethod);

        /*
            GET -> USER aur ADMIN dono allowed
            POST -> sirf ADMIN
            PUT -> sirf ADMIN
            PATCH -> sirf ADMIN
            DELETE -> sirf ADMIN
        */

        if (httpMethod.equals("GET")) {

            response.setHeader(
                    "X-Authorization",
                    "Allowed"
            );

            System.out.println("Authorization successful");

            return true;
        }

        if (role.equals("ADMIN")) {

            response.setHeader(
                    "X-Authorization",
                    "Allowed"
            );

            System.out.println("Authorization successful");

            return true;
        }

        // User ke paas permission nahi hai

        response.setStatus(
                HttpServletResponse.SC_FORBIDDEN
        );

        response.setContentType("application/json");

        response.getWriter().write("""
                {
                    "success": false,
                    "message": "Forbidden! You do not have permission"
                }
                """);

        return false;
    }
}