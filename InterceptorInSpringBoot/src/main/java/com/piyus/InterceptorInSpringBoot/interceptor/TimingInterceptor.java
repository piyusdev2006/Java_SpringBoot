package com.piyus.InterceptorInSpringBoot.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TimingInterceptor implements HandlerInterceptor {

    private static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        long startTime = System.currentTimeMillis();

        // Request ke andar start time store kar diya
        request.setAttribute(START_TIME, startTime);

        System.out.println("Timer started...");

        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ) throws Exception {

        Long startTime = (Long) request.getAttribute(START_TIME);

        if (startTime != null) {

            long endTime = System.currentTimeMillis();

            long executionTime = endTime - startTime;

            System.out.println(
                    "Total Request Time: " + executionTime + " ms"
            );

            // Response header
            response.setHeader(
                    "X-Response-Time",
                    executionTime + " ms"
            );
        }
    }
}