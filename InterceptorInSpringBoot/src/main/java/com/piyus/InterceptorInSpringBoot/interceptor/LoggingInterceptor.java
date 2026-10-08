package com.piyus.InterceptorInSpringBoot.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception
    {

        // hander jo aa rha hai wo controller hai
        // aur use hum collect kar rhe hai HandlerMethod naam ke Object mw
        //HandlerMethod method = (HandlerMethod)handler;

        System.out.println("Incoming request......");

        System.out.println("HTTP Method: "+ request.getMethod());
        System.out.println("Request URI: "+ request.getRequestURI());
        System.out.println("Request Params: "+ request.getQueryString());
        System.out.println("Client IP: "+ request.getRemoteAddr());
        System.out.println("TOken Header: "+ request.getHeader("token"));

        if(handler instanceof HandlerMethod handlerMethod){
            System.out.println("controller: "+ handlerMethod.getBeanType().getName());
            System.out.println("controller Method: "+ handlerMethod.getMethod().getName());
        }

        return true;
    }

    /*
        @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) throws Exception {}
    * */

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ) throws Exception
    {
        System.out.println("Response Status: " + response.getStatus());
    }

}

