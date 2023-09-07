package com.project.todolistbackend.exceptions;

public class TodoListNotFoundException extends Exception {
    public TodoListNotFoundException(String message) {
        super(message);
    }
}
