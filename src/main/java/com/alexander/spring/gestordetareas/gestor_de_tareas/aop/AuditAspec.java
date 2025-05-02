package com.alexander.spring.gestordetareas.gestor_de_tareas.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspec {

    private static final Logger logger = LoggerFactory.getLogger(AuditAspec.class);

    //Auditoria para metodos del servicio
    @Around("execution(* com.alexander.spring.gestordetareas.service.*.*(..))")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable{
        
        //Registramos el inicio
        logger.info("[AOP] Método ejecutado: {} | Parámetros : {}", joinPoint.getSignature(), joinPoint.getArgs());

        Object result = joinPoint.proceed();
        
        //Registra el tiempo de ejecucion
        Long duration = System.currentTimeMillis();
        logger.info("[AOP] Tiempo de ejecución: {} ms", duration);

        return result;
    }

}
