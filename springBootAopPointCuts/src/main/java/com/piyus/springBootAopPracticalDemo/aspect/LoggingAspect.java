package com.piyus.springBootAopPracticalDemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    // interceptor Method using {"execution"} designator
//    @Before("execution(* com.piyus.springBootAopPracticalDemo.service.StudentService.* (..))")
//    public void logBeforeMethod(){
//
//        System.out.println("Method Intercepted");
//    }

    // interceptor Method using {"within"} designator
//    @Before("within(com.piyus.springBootAopPracticalDemo.service.StudentService)")
//    public void logBeforeMethod(){
//
//        System.out.println("Method Intercepted");
//    }


    // interceptor Method u sing {"@annotation"} designator
//    @Before("@annotation(jdk.jfr.Timestamp)")
//    public void logBeforeMethod(){
//
//        System.out.println("Method Intercepted");
//    }


//    // interceptor Method using {"bean"} designator
//    @Before("bean(studentService)")
//    public void logBeforeMethod(){
//
//        System.out.println("Method Intercepted");
//    }

    // combining two or more designator to intercept Method  od different classes using Logical operators
    @Before("bean(studentService) || bean(studentController)")
    public void logBeforeMethod(){

        System.out.println("Method Intercepted");
    }



    @Before("within(com.piyus.springBootAopPracticalDemo.service..*)" +
            "&&" + "execution(public * *(..))" )
    public void logBeforeMethod2(){

        System.out.println("Method Intercepted");
    }


    // Named PointCuts
    @Pointcut("within(com.piyus.springBootAopPracticalDemo.service..*)" +
            "&&" + "execution(public * *(..))")
    public void logPointCut(){
        // empty body always
    }

    @Before("logPointCut()")
    public void logBeforeMethod3(){

        System.out.println("Method Intercepted");
    }

    // using points from generic pointscuts class "ApplicationPointCuts"
    @Before("com.piyus.springBootAopPracticalDemo.aspect.ApplicationPointCuts.ApplicationPointcuts.publicServiceMethod()")
    public void logBeforeMethod4(){

        System.out.println("Method Intercepted");
    }

    @After("logPointCut()")
    public void logAfterReturning(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("Method Intercepted");
    }


//    @Before("execution(com.piyus.springBootAopPracticalDemo.dto.Student " +
//            "com.piyus.springBootAopPracticalDemo.service.StudentService.createStudent("
//            + "com.piyus.springBootAopPracticalDemo.dto.Student))")
//    public void logBeforeMethod2(){
//        System.out.println("Method Intercepted");
//    }


    // custom annotation ka use krke hum method ko intercept kar sakte hai
//    @Around("@annotation(<annotationPathHere>)")
//    public void logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable{
//        long startTime = System.currentTimeMillis();
//
//        joinPoint.proceed();
//
//        long endTime = System.currentTimeMillis();
//
//        System.out.println("Time taken: " + (endTime - startTime));
//    }


    @Before("@within(org.springframework.stereotype.Service)")
    public void logAfterReturning4(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Method Intercepted");
    }

    @Before("@target(org.springframework.stereotype.Service)")
    public void logAfterReturning5(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Method Intercepted");
    }



//    @Before("args(com.piyus.springBootAopPracticalDemo.dto.Student)" +
//    "&&" +
//    "within(com.piyus.springBootAopPracticalDemo.service..*)")
//    public void logAfterReturning6(ProceedingJoinPoint joinPoint) throws Throwable{
//        System.out.println("Method Intercepted");
//    }


    @Before("@args(jdk.jfr.Timestamp)" +
            "&&" +
            "within(com.piyus.springBootAopPracticalDemo.service..*)")
    public void logAfterReturning7(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("Method Intercepted");
    }



    // this and target designators
    //target works at class level in which it takes classPath with classname
    // and @target also works at class level based on annotation and it takes
    // path of that annotation with annotation name which is applied on that class
    @Before("target(com.piyus.springBootAopPracticalDemo.service.StudentService)")
    public void logAfterReturning8(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("Method Intercepted");
    }

    @Before("this(com.piyus.springBootAopPracticalDemo.service.StudentService)")
    public void logAfterReturning9(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("Method Intercepted");
    }

}
