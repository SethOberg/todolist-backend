package com.project.todolistbackend.controllers;

import com.project.todolistbackend.Models.TodoListItem;
import com.project.todolistbackend.services.TodoListItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/todolistitems")
public class TodoListItemController {
    @Autowired
    private TodoListItemServiceImpl todoListItemService;

    @GetMapping
    public Collection<TodoListItem> getAllTodoListItems() {
        return todoListItemService.findAll();
    }

}
