package com.communicator.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.communicator.model.GenericResponse;

@Aspect
@Component
public class ExecutionTimeAdvice {

    @Around("execution(* com.shaik.helper..*(..)))")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        GenericResponse response = (GenericResponse) point.proceed();
        response.setTimeTaken((System.currentTimeMillis() - startTime)+" ms");
        System.out.println("Class Name: "+ point.getSignature().getDeclaringTypeName() +"\nMethod Name: "+ point.getSignature().getName() +
        		"\nTime taken : " + response.getTimeTaken());
        return response;
    }
}
