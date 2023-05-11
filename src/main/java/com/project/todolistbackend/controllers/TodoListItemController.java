package com.project.todolistbackend.controllers;

import com.project.todolistbackend.Models.TodoListItem;
import com.project.todolistbackend.services.TodoListItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/todolistitems")
public class TodoListItemController {
    @Autowired
    private TodoListItemServiceImpl todoListItemService;

    @GetMapping
    public Collection<TodoListItem> getAllTodoListItems() {
        return todoListItemService.findAll();
    }

    @GetMapping("/{id}")
    public TodoListItem getTodoListItem(@PathVariable UUID id) {
        return  todoListItemService.findById(id);
    }

    @PostMapping
    public TodoListItem addTodoListItem(@RequestBody TodoListItem todoListItem) {
        return todoListItemService.add(todoListItem);
    }

    @PutMapping
    public TodoListItem updateTodoListItem(@RequestBody TodoListItem todoListItem) {
        return todoListItemService.update(todoListItem);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoListItem(@PathVariable UUID id) {
        todoListItemService.deleteById(id);
    }


}
