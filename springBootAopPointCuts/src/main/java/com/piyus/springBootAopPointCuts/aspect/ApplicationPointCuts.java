package com.piyus.springBootAopPointCuts.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class ApplicationPointCuts {


    public class ApplicationPointcuts {

        @Pointcut("within(com.piyus.springBootAopPointCuts.controller..*)")
        public void controllerLayer() {
            // emoty body
        }

        @Pointcut("within(com.piyus.springBootAopPointCuts.service..*)")
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
