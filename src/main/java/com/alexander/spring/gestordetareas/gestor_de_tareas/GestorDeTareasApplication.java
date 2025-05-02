package com.alexander.spring.gestordetareas.gestor_de_tareas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy //Habilitamos el AOP
public class GestorDeTareasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorDeTareasApplication.class, args);
	}

}
