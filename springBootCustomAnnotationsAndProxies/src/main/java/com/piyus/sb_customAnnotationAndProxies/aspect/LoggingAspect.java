package com.piyus.sb_customAnnotationAndProxies.aspect;

import com.piyus.sb_customAnnotationAndProxies.annotation.TrackExecutionTime;
import com.piyus.sb_customAnnotationAndProxies.dto.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    //    @Before("@annotation(jdk.jfr.Timestamp)")
    //    public void logBeforeMethod() {
    //        System.out.println("Log Before Method");
    //    }


    // interceptor method - type matching method
//    @Around("@annotation(" +
//            "com.piyus.sb_customAnnotationAndProxies.annotation.TrackExecutionTime)")
//    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        long startTime = System.currentTimeMillis();
//
//        try{
//            // proceed() might return something like string , int , Object ect..
//            // and we return it to client as it is , interceptor khud na consume kr jaye
//            // toh hum iss method ka return type void nhi rakh sakte , kyoki client ko null milega
//            // kyoki around kh jayega
//            // toh hum return kr de rhe taaki joint aage return kr de
//            // aur method ka return type bhi Object ho jayega , kyoki pta nhi kya aane wala hai
//
//
//            return joinPoint.proceed();
//        }
//        finally{
//            long executionTime = System.currentTimeMillis() - startTime;
//            long duration = executionTime;
//
//            // extracting method method using joinPoint method
//            String methodName = joinPoint.getSignature().getName();
//
//            System.out.println("Time taken by : " + methodName + ": " + duration);
//
//        }
//    }


    // binding form method
    @Around("@annotation(trackExecutionTime)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint,
                                       TrackExecutionTime trackExecutionTime) throws Throwable {

        long startTime = System.currentTimeMillis();

        try {
            return joinPoint.proceed();

        } finally {
            long executionTime = System.currentTimeMillis() - startTime;
            long duration = executionTime;

            String operation = trackExecutionTime.operation();
            if (operation.isBlank()) {
                joinPoint.getSignature().getName();
            }

            // extracting method using joinPoint method
//            String methodName = joinPoint.getSignature().getName();

            long warningThreshold = trackExecutionTime.warnAfter();

            if(duration > warningThreshold){
                System.out.println("Slow operation Alert " +
                        "Time taken by : " + operation + ": " + duration);
            }
            else {
                System.out.println("Time taken by : " + operation + ": " + duration);
            }

        }
    }
}

