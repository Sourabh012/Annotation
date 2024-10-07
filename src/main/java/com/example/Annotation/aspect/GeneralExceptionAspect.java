package com.example.Annotation.aspect;

import com.example.Annotation.exception.HandleGeneralException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GeneralExceptionAspect {

    @Pointcut("@within(com.example.Annotation.exception.HandleGeneralException) || @annotation(com.example.Annotation.exception.HandleGeneralException)")
    public void handleGeneralExceptionPointcut() {}

    @AfterThrowing(pointcut = "handleGeneralExceptionPointcut()", throwing = "ex")
    public void handleGeneralException(Exception ex) {
        // Log or process any general exception
        System.out.println("General Exception handled: " + ex.getMessage());
    }
}

