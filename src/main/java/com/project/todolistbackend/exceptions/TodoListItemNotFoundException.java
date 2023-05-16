package com.project.todolistbackend.exceptions;

public class TodoListItemNotFoundException extends Exception {
    public TodoListItemNotFoundException(String message) {
        super(message);
    }
}
