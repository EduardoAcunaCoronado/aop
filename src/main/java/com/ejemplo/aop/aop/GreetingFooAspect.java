package com.ejemplo.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(1)
@Aspect
@Component
public class GreetingFooAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.ejemplo.aop.services.GreetingService.*(..))")
    public void loggerBefore(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        log.info("Foo Antes: " + method + " con los argumentos " + args);
    }

    @After("execution(* com.ejemplo.aop.services.GreetingService.*(..))")
    public void loggerAfter(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        log.info("Foo Después: " + method + " con los argumentos " + args);
    }

    @AfterReturning("execution(* com.ejemplo.aop.services.GreetingService.*(..))")
    public void loggerAfterReturning(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        log.info("Foo Después de retornar: " + method + " con los argumentos " + args);
    }

    @AfterThrowing("execution(* com.ejemplo.aop.services.GreetingService.*(..))")
    public void loggerAfterThrowing(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        log.info("Foo Después de lanzar la excepción: " + method + " con los argumentos " + args);
    }

    @Around("execution(* com.ejemplo.aop.services.*.*(..))")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        Object result;
        try {
            log.info("Foo Around: El método " + method + " con los argumentos " + args);
            result = joinPoint.proceed();
            log.info("Foo Around: El método " + method + " retorna el resultado " + result);
            return result;
        } catch (Throwable e) {
            log.error("Foo Around: Error en el método " + method + " con los argumentos " + args);
            throw e;
        }
    }

}
