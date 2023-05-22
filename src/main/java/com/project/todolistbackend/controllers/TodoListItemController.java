package com.project.todolistbackend.controllers;

import com.project.todolistbackend.Models.TodoList;
import com.project.todolistbackend.Models.TodoListItem;
import com.project.todolistbackend.exceptions.TodoListItemNotFoundException;
import com.project.todolistbackend.services.TodoListItemServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todolist item found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TodoList.class))}),
            @ApiResponse(responseCode = "404", description = "Todolist item not found",
                    content = @Content)
    })
    @Operation(summary = "Get a todolist item by its id")
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todolist item updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TodoList.class))}),
            @ApiResponse(responseCode = "404", description = "Todolist item not found",
                    content = @Content)
    })
    @Operation(summary = "Update a todolist item by id in requestbody")
    @PutMapping
    public ResponseEntity updateTodoListItem(@RequestBody TodoListItem todoListItem) {

        try {
            TodoListItem todoListItemOld = todoListItemService.findById(todoListItem.getId());
            return new ResponseEntity(todoListItemService.update(todoListItem), HttpStatus.OK);
        } catch (TodoListItemNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todolist item deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TodoList.class))}),
            @ApiResponse(responseCode = "404", description = "Todolist item not found",
                    content = @Content)
    })
    @Operation(summary = "Delete a todolist item by its id")
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
