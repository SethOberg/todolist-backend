package com.project.todolistbackend.controllers;

import com.project.todolistbackend.Models.TodoList;
import com.project.todolistbackend.Models.TodoListItem;
import com.project.todolistbackend.services.TodoListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/todolists")
public class TodoListController {
    @Autowired
    private TodoListServiceImpl todoListService;

    @GetMapping
    public Collection<TodoList> getAllTodoLists() {
        return todoListService.findAll();
    }

    @GetMapping("/{id}")
    public TodoList getTodoList(@PathVariable UUID id) {
        return todoListService.findById(id);
    }

    @PostMapping
    public TodoList addTodoList(@RequestBody TodoList todoList) {
        return todoListService.add(todoList);
    }

    @PutMapping("/{id}")
    public TodoList updateTodoList(@RequestBody TodoList todoList) {
        return todoListService.update(todoList);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoList(@PathVariable UUID id) {
        todoListService.deleteById(id);
    }

}
