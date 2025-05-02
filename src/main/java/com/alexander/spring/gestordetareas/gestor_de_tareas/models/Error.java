package com.alexander.spring.gestordetareas.gestor_de_tareas.models;

import java.util.Date;

public class Error {

    private String title;
    private String message;
    private int status;
    private Date date;

    public Error(){

    }

    public Error(String title, String message, int status, Date date) {
        this.title = title;
        this.message = message;
        this.status = status;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
