package com.project.todolistbackend.controllers;

import com.project.todolistbackend.Models.TodoListItem;
import com.project.todolistbackend.exceptions.TodoListItemNotFoundException;
import com.project.todolistbackend.services.TodoListItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getTodoListItem(@PathVariable UUID id) {
        try {
            return new ResponseEntity(todoListItemService.findById(id), HttpStatus.OK);
        } catch (TodoListItemNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public TodoListItem addTodoListItem(@RequestBody TodoListItem todoListItem) {
        return todoListItemService.add(todoListItem);
    }

    @PutMapping
    public ResponseEntity updateTodoListItem(@RequestBody TodoListItem todoListItem) {

        try {
            TodoListItem todoListItemOld = todoListItemService.findById(todoListItem.getId());
            return new ResponseEntity(todoListItemService.update(todoListItem), HttpStatus.OK);
        } catch (TodoListItemNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodoListItem(@PathVariable UUID id) {

        try {
            todoListItemService.deleteById(id);
            return new ResponseEntity("Todolist item deleted", HttpStatus.OK);
        } catch (TodoListItemNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
