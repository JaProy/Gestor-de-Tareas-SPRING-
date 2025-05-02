package com.alexander.spring.gestordetareas.gestor_de_tareas.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    private static final Logger logger = LoggerFactory.getLogger(SecurityAspect.class);

    @Before("@annotation(com.alexander.spring.gestordetareas.gestor_de_tareas.aop.AdminOnly)")
    public void checkAdminAccess(JoinPoint joinPoint){
        //Simulo la logica de autenticacion
        boolean isAdmin = true; //Obtenemos el contexto de seguridad

        if(!isAdmin){
            logger.error("[AOP-SECURITY] Acceso denegado a: {}", joinPoint.getSignature());
            throw new SecurityException("Acceso restringido a administradores");
        }

        logger.info("[AOP-SECURITY] Acceso permitido a admin");
    }

}
