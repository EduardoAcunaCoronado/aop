package com.ejemplo.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class GreetingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.ejemplo.aop.services.GreetingService.*(..))")
    public void loggerBefore(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        log.info("Antes: " + method + " con los argumentos " + args);
    }

    @After("execution(* com.ejemplo.aop.services.GreetingService.*(..))")
    public void loggerAfter(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        log.info("Después: " + method + " con los argumentos " + args);
    }

    @AfterReturning("execution(* com.ejemplo.aop.services.GreetingService.*(..))")
    public void loggerAfterReturning(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        log.info("Después de retornar: " + method + " con los argumentos " + args);
    }

    @AfterThrowing("execution(* com.ejemplo.aop.services.GreetingService.*(..))")
    public void loggerAfterThrowing(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        log.info("Después de lanzar la excepción: " + method + " con los argumentos " + args);
    }

    @Around("execution(* com.ejemplo.aop.services.*.*(..))")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        Object result;
        try {
            log.info("Around: El método " + method + " con los argumentos " + args);
            result = joinPoint.proceed();
            log.info("Around: El método " + method + " retorna el resultado " + result);
            return result;
        } catch (Throwable e) {
            log.error("Around: Error en el método " + method + " con los argumentos " + args);
            throw e;
        }
    }

}
