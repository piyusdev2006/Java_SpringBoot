package com.piyus.springBootAopPracticalDemo.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class ApplicationPointCuts {


    public class ApplicationPointcuts {

        @Pointcut("within(com.piyus.springBootAopPracticalDemo.controller..*)")
        public void controllerLayer() {
            // emoty body
        }

        @Pointcut("within(com.piyus.springBootAopPracticalDemo.service..*)")
        public void serviceLayer() {
            // empty body
        }

        @Pointcut("execution(public * * (..))")
        public void publicMethod() {
            // empty body
        }

        @Pointcut("serviceLayer() && publicMethod()")
        public void publicServiceMethod() {
            // empty body
        }

        @Pointcut("execution(* *.get* (..))")
        public void getterMethod() {
            // empty body
        }
    }
}
