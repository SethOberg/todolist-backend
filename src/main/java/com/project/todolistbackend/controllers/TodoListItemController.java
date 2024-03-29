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
@CrossOrigin("*")
@RequestMapping("/todolistitems")
public class TodoListItemController {
    @Autowired
    private TodoListItemServiceImpl todoListItemService;


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TodoListItem.class))
            }),
            @ApiResponse(responseCode = "404", description = "No todolist items found",
                    content = @Content)
    })
    @Operation(summary = "Get all Todolist items")
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todolist item added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TodoListItem.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)
    })
    @Operation(summary = "Add a new todolist item")
    @PostMapping
    public ResponseEntity addTodoListItem(@RequestBody TodoListItem todoListItem) {
        TodoListItem added = todoListItemService.add(todoListItem);
        if(added != null) {
            return new ResponseEntity<>(added, HttpStatus.CREATED);
        }
        return new ResponseEntity("Error adding item", HttpStatus.BAD_REQUEST);
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

    @Operation(summary = "Mark a todolist item completed or not completed by id")
    @PutMapping("/{id}")
    public ResponseEntity todolistItemCompleted(@PathVariable UUID id) {

        try {
            TodoListItem todoListItemOld = todoListItemService.findById(id);
            todoListItemOld.setCompleted(!todoListItemOld.isCompleted());
            return new ResponseEntity(todoListItemService.update(todoListItemOld), HttpStatus.OK);
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
