package com.alexander.spring.gestordetareas.gestor_de_tareas.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alexander.spring.gestordetareas.gestor_de_tareas.models.Task;
import com.alexander.spring.gestordetareas.gestor_de_tareas.service.TaskService;


@Controller
public class TaskController {

    TaskService taskService;

    // Inyección por constructor (recomendado)
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //Vista HTML a pagina principal
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("title", "Gestor de Tareas");
        return "home";
    }

    //Vista HTML al listado de tareas
    @GetMapping("/tasks")
    public String tasksGet(Model model){
        model.addAttribute("title", "Lista de tareas");
        if(taskService.findAll().equals(null) || taskService.findAll().isEmpty()){
            model.addAttribute("tasksNot", "No tienes tareas registradas!");
        }else{
            model.addAttribute("tasks", taskService.findAll());
        }
        return "tasks";
    }

    //Vista json de las tareas
    @GetMapping("/tasks-view")
    @ResponseBody
    public List<Task> tasksView(){
        return taskService.findAll();
    }

    //Vista HTML a la creacion de tareas
    @GetMapping("/task/create")
    public String tasksCreateG(Model model){
        model.addAttribute("titleP", "Crear Tarea");
        model.addAttribute("task", new Task()); //Objeto task vacio
        return "create-task";
    }

    @PostMapping("/task/create")
    public String tasksCreateP(Task task, RedirectAttributes redirectAtt){
        taskService.save(task);
        redirectAtt.addFlashAttribute("taskCreated","Tarea " + task.getTitle() + " creada!");
        return "redirect:/task/create";
    }

    //Vista HTML a los detalles de la tarea
    @GetMapping("/task/{id}")
    public String taskDetails(@PathVariable(required = false) Long id , Model model){
        model.addAttribute("title", "Detalles de Tarea");
        Task taskD = taskService.getTaskById(id);
        model.addAttribute("taskD", taskD);
        model.addAttribute("taskC", "Tarea creada el: " + taskD.getCreatedAt().getDayOfMonth() + " de " + taskD.getCreatedAt().getMonth() + " del " + taskD.getCreatedAt().getYear() + ", a las " + taskD.getCreatedAt().getHour() + ":" + taskD.getCreatedAt().getMinute() + " hrs");
        model.addAttribute("taskU", "Ultima actualizacion: " + taskD.getUpdateAt().getDayOfMonth() + " de " + taskD.getUpdateAt().getMonth() + " del " + taskD.getUpdateAt().getYear() + ", a las " + taskD.getUpdateAt().getHour() + ":" +taskD.getUpdateAt().getMinute() + " hrs");
        return "task-detail";
    }

    //Vista HTML a la edicion de tareas
    @GetMapping("/task/{id}/edit")
    public String taskEditG(@PathVariable(required = false) Long id ,Model model){
        model.addAttribute("title", "Editar Tarea");
        Task taskTE = taskService.getTaskById(id);
        if(taskTE.equals(null)){
            model.addAttribute("taskEE", "Ocurrio un error al acceder a la tarea seleccionada.");
        }else{
            model.addAttribute("taskE", taskTE);
        }
        return "task-edit";
    }

    @PostMapping("/task/{id}/edit")
    public String taskEditP(@PathVariable Long id, Model model,@ModelAttribute("taskE") Task taskE, RedirectAttributes redirectAtt){
        model.addAttribute("title", "Editar tarea");
        taskService.save(taskE);    //Guardamos la tarea en nuestro service
        redirectAtt.addFlashAttribute("taskSave","Tarea guardada con exito!");
        return "redirect:/tasks";
    }

    //Metodo POST para eliminar una tarea
    @PostMapping("/task/{id}/delete")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAtt){
        String taskT = taskService.getTaskById(id).getTitle();   //Variable temporal para almazenar el titulo de la tarea que eliminare (por si se nesesita mas adelante)
        taskService.delete(id); //Elimino la tarea con el metodo de mi service
        redirectAtt.addFlashAttribute("taskDelete", "Tarea "+ taskT +" eliminada con exito!");  //Paso un mensaje de tarea eliminada, para informarle al usuario que tarea se elimino
        return "redirect:/tasks";
    }

}
