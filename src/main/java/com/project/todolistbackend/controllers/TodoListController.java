package com.project.todolistbackend.controllers;

import com.project.todolistbackend.Models.TodoList;
import com.project.todolistbackend.Models.TodoListItem;
import com.project.todolistbackend.services.TodoListItemServiceImpl;
import com.project.todolistbackend.services.TodoListServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/todolists")
public class TodoListController {
    @Autowired
    private TodoListServiceImpl todoListService;
    @Autowired
    private TodoListItemServiceImpl todoListItemService;

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

    @PutMapping("/addTodoListItem/{id}")
    public void addTodoListItem(@PathVariable UUID id, @RequestBody TodoListItem todoListItem) {
        TodoList todoList = todoListService.findById(id);
        if(todoList != null) {
            todoList.addTodoListItem(todoListItem);
            todoListItem.setTodoList(todoList);
        }
        todoListService.update(todoList);
    }

    @PutMapping("/updateTodoListItem/{id}")
    public void updateTodoListItem(@PathVariable UUID id, @Valid @RequestBody TodoListItem todoListItemNewValue) {
        TodoListItem todoListItem = todoListItemService.findById(id);
        TodoList todoList = todoListItem.getTodoList();
        todoListItemNewValue.setTodoList(todoList);
        todoListItemService.update(todoListItemNewValue);
    }

}
