package com.alexander.spring.gestordetareas.gestor_de_tareas.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.alexander.spring.gestordetareas.gestor_de_tareas.exceptions.TaskNotFoundException;
import com.alexander.spring.gestordetareas.gestor_de_tareas.models.Error;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public String noResourceFoundException(Model model, Exception ex){
        model.addAttribute("titleP", "Error 404");
        
        Error error = new Error("Error 404",ex.getMessage(),HttpStatus.NOT_FOUND.value(),new Date());
        
        model.addAttribute("titleE", error.getTitle());
        model.addAttribute("message", error.getMessage());
        model.addAttribute("status", error.getStatus());
        model.addAttribute("date", error.getDate().toString());

        return "error/404";
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public String taskNotFoundException(Model model, Exception ex){
        model.addAttribute("titleP", "Error 404");
        
        Error error = new Error("Tarea no encontrada 404",ex.getMessage(),HttpStatus.NOT_FOUND.value(),new Date());
        
        model.addAttribute("titleE", error.getTitle());
        model.addAttribute("message", error.getMessage());
        model.addAttribute("status", error.getStatus());
        model.addAttribute("date", error.getDate().toString());

        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String exception(Model model, Exception ex){
        model.addAttribute("titleP", "Error 500");

        Error error = new Error("Ocurrio un error inesperado", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date());

        model.addAttribute("titleE", error.getTitle());
        model.addAttribute("message", error.getMessage());
        model.addAttribute("status", error.getStatus());
        model.addAttribute("date", error.getDate().toString());
        
        return "error/500";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentException(Model model, Exception ex){
        model.addAttribute("titleP", "Error 400");

        Error error = new Error("Error 400", ex.getMessage(), HttpStatus.BAD_REQUEST.value(), new Date());

        model.addAttribute("titleE", error.getTitle());
        model.addAttribute("message", error.getMessage());
        model.addAttribute("status", error.getStatus());
        model.addAttribute("date", error.getDate().toString());
        return "error/400";
    }

}
