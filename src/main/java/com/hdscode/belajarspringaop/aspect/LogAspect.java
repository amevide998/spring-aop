package com.hdscode.belajarspringaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {


    @Pointcut("target(com.hdscode.belajarspringaop.service.HelloService)")
    public void helloServiceMethod(){
    }

    @Pointcut("execution(* com.hdscode.belajarspringaop.service.HelloService.*(java.lang.String))")
    public void helloServiceMethod2(){
    }

    @Pointcut("execution(* com.hdscode.belajarspringaop.service.HelloService.*(..))")
    public void pointCutServicePackage(){}

    @Pointcut("bean(*Service)")
    public void beanService(){}

    @Pointcut("pointCutServicePackage() && beanService()")
    public void helloServiceMethod3(){}


    @Before("helloServiceMethod()")
    public void logHelloServiceMethod(JoinPoint joinPoint){
        log.info("before {}.{} - with args : {} ",joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(),  Arrays.toString(joinPoint.getArgs()));
    }

    @Around("helloServiceMethod()")
    public String aroundHelloServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String name = joinPoint.getTarget().getClass().getName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        try{
            log.info("around before {}.{} - with args : {} ",name, method, args);
            return String.valueOf(joinPoint.proceed());
        }catch (Throwable throwable){
            log.info("around throw {}.{} - with args : {} ",name, method, args);
            throw throwable;
        }finally {
            log.info("around after {}.{} - with return : {} ",name, method, joinPoint.proceed());
        }

    }

    @Before("helloServiceMethod2()")
    public void logHelloServiceMethod2(JoinPoint joinPoint){
        log.info("execute method {}.{} - with args : {} ",joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(),  Arrays.toString(joinPoint.getArgs()));
    }

    @Before("helloServiceMethod3()")
    public void logHelloServiceMethod3(JoinPoint joinPoint){
        log.info("execute multiple poincut - method {}.{} - with args : {} ",joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(),  Arrays.toString(joinPoint.getArgs()));
    }

    @Before("helloServiceMethod2() && args(name)")
    public void logHelloServiceMethod2GetArgs(String name){
        log.info("execute - logHelloServiceMethod2GetArgs with parama : {} ",name);
    }


}
