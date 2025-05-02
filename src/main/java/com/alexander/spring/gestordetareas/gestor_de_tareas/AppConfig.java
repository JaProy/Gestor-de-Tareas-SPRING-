package com.alexander.spring.gestordetareas.gestor_de_tareas;

import com.alexander.spring.gestordetareas.gestor_de_tareas.interceptors.LogginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private LogginInterceptor loggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Aplica el interceptor a todas las rutas
        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/**");
    }
}