package com.alexander.spring.gestordetareas.gestor_de_tareas.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LogginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
                //Registra el inicio de la solicitud y guarda el tiempo de inicio
                Long startTime = System.currentTimeMillis();
                request.setAttribute("startTime", startTime);

                logger.info("[Interceptor] Inicio: {} {}", request.getMethod(),request.getRequestURL());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception 
            {
                //Calcula la duracion de la solicitud
                Long startTime = (Long)request.getAttribute("startTime");
                long duration = System.currentTimeMillis() - startTime;

                logger.info("[Interceptor] Tiempo de respuesta: {} ms - Status: {}",duration,response.getStatus());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
