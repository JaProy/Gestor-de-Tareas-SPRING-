package com.alexander.spring.gestordetareas.gestor_de_tareas.exceptions;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(String exception){
        super(exception);
    }
}
