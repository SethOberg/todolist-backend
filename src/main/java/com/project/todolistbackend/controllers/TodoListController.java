package com.project.todolistbackend.controllers;

import com.project.todolistbackend.Models.TodoList;
import com.project.todolistbackend.Models.TodoListItem;
import com.project.todolistbackend.exceptions.TodoListItemNotFoundException;
import com.project.todolistbackend.exceptions.TodoListNotFoundException;
import com.project.todolistbackend.services.TodoListItemServiceImpl;
import com.project.todolistbackend.services.TodoListServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
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
    public ResponseEntity getTodoList(@PathVariable UUID id) {
        try {
            return new ResponseEntity(todoListService.findById(id), HttpStatus.OK);
        } catch (TodoListNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public TodoList addTodoList(@RequestBody TodoList todoList) {
        return todoListService.add(todoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTodoList(@RequestBody TodoList todoList) {
        try {
            return new ResponseEntity(todoListService.update(todoList), HttpStatus.OK);
        } catch (TodoListNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodoList(@PathVariable UUID id) {
        try {
            todoListService.deleteById(id);
            return new ResponseEntity("User deleted", HttpStatus.OK);
        } catch (TodoListNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/addTodoListItem/{id}")
    public ResponseEntity addTodoListItem(@PathVariable UUID id, @RequestBody TodoListItem todoListItem) {
        TodoList todoList;

                try {
                    todoList = todoListService.findById(id);

                    if(todoList != null) {
                        todoList.addTodoListItem(todoListItem);
                        todoListItem.setTodoList(todoList);
                    }

                    return new ResponseEntity(todoListService.update(todoList), HttpStatus.OK) ;
                } catch (TodoListNotFoundException e) {
                   return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
                }

    }

    //Todo: move to todolistitemcontroller
    @PutMapping("/updateTodoListItem/{id}")
    public void updateTodoListItem(@PathVariable UUID id, @Valid @RequestBody TodoListItem todoListItemNewValue) {
        TodoListItem todoListItem = null;
        try {
            todoListItem = todoListItemService.findById(id);
            if(todoListItem != null) {
                TodoList todoList = todoListItem.getTodoList();
                if(todoListItem != null) {
                    todoListItemNewValue.setTodoList(todoList);
                    todoListItemService.update(todoListItemNewValue);
                }
            }
        } catch (TodoListItemNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
