package com.example.Annotation.aspect;

import com.example.Annotation.exception.HandleHttpStatusCodeException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

@Aspect
@Component
public class HttpStatusCodeExceptionAspect {

    @Around("@annotation(handleHttpStatusCodeException)")
    public ResponseEntity<?> handleHttpStatusCodeException(ProceedingJoinPoint joinPoint, HandleHttpStatusCodeException handleHttpStatusCodeException) throws Throwable {
        try {
            // Proceed with the method execution
            return (ResponseEntity<?>) joinPoint.proceed();
        } catch (HttpStatusCodeException ex) {
            // Catch the HttpStatusCodeException and return the response
            return ResponseEntity.status(ex.getStatusCode())
                    .headers(ex.getResponseHeaders())
                    .body(ex.getResponseBodyAsString());
        }
    }
}
