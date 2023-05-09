package com.project.todolistbackend.controllers;

import com.project.todolistbackend.Models.TodoList;
import com.project.todolistbackend.services.TodoListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/todolists")
public class TodoListController {
    @Autowired
    private TodoListServiceImpl todoListService;

    @GetMapping
    public Collection<TodoList> getAllTodoLists() {
        return todoListService.findAll();
    }

}
