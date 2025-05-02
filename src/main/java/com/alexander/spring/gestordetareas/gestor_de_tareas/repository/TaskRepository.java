package com.alexander.spring.gestordetareas.gestor_de_tareas.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.alexander.spring.gestordetareas.gestor_de_tareas.exceptions.TaskNotFoundException;
import com.alexander.spring.gestordetareas.gestor_de_tareas.models.Task;

@Repository
public class TaskRepository {

    //Simulamos la base de datos en memoria
    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();

    //Simulador de IDs autoincremental
    private final AtomicLong counter = new AtomicLong(1);

    public Task save(Task task){
        if(task.getId() == null){   //Compruebo que la tarea ingresada no tenga id
            Long newId = counter.getAndIncrement(); //Creo un nuevo id autoincremental
            task.setId(newId);  //Ajusto el id creado en la tarea
            task.setCreatedAt(LocalDateTime.now());
        }
        task.setUpdateAt(LocalDateTime.now());
        tasks.put(task.getId(), task);  //Obtengo el id de la tarea para mi Map, y añado la tarea a la bd
        return task;    //Devuelvo la tarea
    }

    public Optional<Task> findById(Long id){
        return Optional.ofNullable(tasks.get(id));  //Busca una tarea usando su id, si existe devuelte la tarea, si no existe devuelve un null
    }

    public List<Task> findAll(){
        return new ArrayList<>(tasks.values()); //Devuelve todas las tareas almazenadas en tasks y crea una nueva lista con los elementos dentro de ella, para evitar la inmutabilidad
    }

    public void delete(Long id){
        Task taskExisting = tasks.get(id);
        if(taskExisting == null){
            throw new TaskNotFoundException("Tarea con ID " + id + ", no encontrada!");
        }
        tasks.remove(id);   //Remuevo una tarea segun el id dado por el usuario
    }
}
