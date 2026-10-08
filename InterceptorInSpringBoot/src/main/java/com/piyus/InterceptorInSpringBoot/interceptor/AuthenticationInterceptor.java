package com.piyus.InterceptorInSpringBoot.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        System.out.println("Authentication checking...");

        // Header se token nikalna
        String token = request.getHeader("token");

        // Custom response header
        response.setHeader(
                "X-Auth-Interceptor",
                "AuthenticationInterceptor"
        );

        // Token check
        if (token == null || !token.equals("12345")) {

            System.out.println("Authentication failed");

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );

            response.setContentType("application/json");

            response.getWriter().write("""
                    {
                        "success": false,
                        "message": "Unauthorized! Invalid or missing token"
                    }
                    """);

            // Controller execute nahi hoga
            return false;
        }

        // Authentication successful

        response.setHeader(
                "X-Authenticated",
                "true"
        );

        // Demo ke liye role set kar rahe hain
        request.setAttribute("role", "ADMIN");

        if (handler instanceof HandlerMethod handlerMethod) {

            System.out.println(
                    "Authenticated Controller: "
                            + handlerMethod.getBeanType().getName()
            );

            System.out.println(
                    "Authenticated Method: "
                            + handlerMethod.getMethod().getName()
            );
        }

        System.out.println("Authentication successful");

        return true;
    }
}