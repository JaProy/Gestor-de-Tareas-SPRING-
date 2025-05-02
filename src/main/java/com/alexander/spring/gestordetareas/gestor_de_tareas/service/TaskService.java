package com.alexander.spring.gestordetareas.gestor_de_tareas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alexander.spring.gestordetareas.gestor_de_tareas.aop.AdminOnly;
import com.alexander.spring.gestordetareas.gestor_de_tareas.exceptions.TaskNotFoundException;
import com.alexander.spring.gestordetareas.gestor_de_tareas.models.Task;
import com.alexander.spring.gestordetareas.gestor_de_tareas.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task save(Task task){
        //Verificamos que la tarea tenga un titulo
        if(task.getTitle() == null || task.getTitle().trim().isEmpty()){
            throw new IllegalArgumentException("El título es obligatorio");
        }
        //Verificamos que la tarea tenga una descripcion mayor a 5 caracteres
        if(task.getDescription() == null || task.getDescription().trim().length()<5){
            throw new IllegalArgumentException("La descripción debe de tener almenos 5 caracteres");
        }
        System.out.println("PASS");
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id){   //Busco una tarea por ID y mando una excepcion y no existe dicha tarea
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Tarea no encontrada"));
    }

    public Task updateTask(Task task){
        getTaskById(task.getId());  //Verificamos si la tarea existe, si no lanzara un error
        return save(task);  //Reutilizamos el metodo save para uzar las verificaciones
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    @AdminOnly
    public void delete(Long id){
        getTaskById(id); //Lanzamos un error si la tarea no existe!
        taskRepository.delete(id);
    }
}
