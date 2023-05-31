package com.project.todolistbackend.controllers;

import com.project.todolistbackend.Models.Person;
import com.project.todolistbackend.Models.TodoList;
import com.project.todolistbackend.Models.TodoListItem;
import com.project.todolistbackend.exceptions.TodoListItemNotFoundException;
import com.project.todolistbackend.exceptions.TodoListNotFoundException;
import com.project.todolistbackend.services.TodoListItemServiceImpl;
import com.project.todolistbackend.services.TodoListServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@CrossOrigin(origins = "*")
@RequestMapping("/todolists")
public class TodoListController {
    @Autowired
    private TodoListServiceImpl todoListService;
    @Autowired
    private TodoListItemServiceImpl todoListItemService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TodoList.class))
            }),
            @ApiResponse(responseCode = "404", description = "No todolists found",
                    content = @Content)
    })
    @Operation(summary = "Get all Todolists")
    @GetMapping
    public Collection<TodoList> getAllTodoLists() {
        return todoListService.findAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todolist found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TodoList.class))}),
            @ApiResponse(responseCode = "404", description = "Todolist not found",
                    content = @Content)
    })
    @Operation(summary = "Get a todolist by its id")
    @GetMapping("/{id}")
    public ResponseEntity getTodoList(@PathVariable UUID id) {
        try {
            return new ResponseEntity(todoListService.findById(id), HttpStatus.OK);
        } catch (TodoListNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todolist added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TodoList.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)
    })
    @Operation(summary = "Add a new todolist")
    @PostMapping
    public ResponseEntity addTodoList(@RequestBody TodoList todoList) {

        TodoList added = todoListService.add(todoList);
        if(added != null) {
            return new ResponseEntity<>(added, HttpStatus.CREATED);
        }

        return new ResponseEntity("Error when adding todolist", HttpStatus.BAD_REQUEST);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todolist updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TodoList.class))}),
            @ApiResponse(responseCode = "404", description = "Todolist not found",
                    content = @Content)
    })
    @Operation(summary = "Update a todolist using the id defined in the requestbody")
    @PutMapping("/{id}")
    public ResponseEntity updateTodoList(@RequestBody TodoList todoList) {
        try {
            return new ResponseEntity(todoListService.update(todoList), HttpStatus.OK);
        } catch (TodoListNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todolist Deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TodoList.class))}),
            @ApiResponse(responseCode = "404", description = "Todolist not found",
                    content = @Content)
    })
    @Operation(summary = "Delete a todolist by id")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodoList(@PathVariable UUID id) {
        try {
            todoListService.deleteById(id);
            return new ResponseEntity("Todolist deleted", HttpStatus.OK);
        } catch (TodoListNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todolist item added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TodoList.class))}),
            @ApiResponse(responseCode = "404", description = "Todolist not found",
                    content = @Content)
    })
    @Operation(summary = "Add a new todolist item to todo list")
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todolist item added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TodoListItem.class))}),
            @ApiResponse(responseCode = "404", description = "Todolist not found",
                    content = @Content)
    })
    @Operation(summary = "Update a todolist item in todo list")
    @PutMapping("/updateTodoListItem/{id}")
    public ResponseEntity updateTodoListItem(@PathVariable UUID id, @Valid @RequestBody TodoListItem todoListItemNewValue) {
        TodoListItem todoListItem = null;
        try {
            todoListItem = todoListItemService.findById(id);
            if(todoListItem != null) {
                TodoList todoList = todoListItem.getTodoList();
                if(todoListItem != null) {
                    todoListItemNewValue.setTodoList(todoList);
                    return new ResponseEntity(todoListItemService.update(todoListItemNewValue), HttpStatus.OK);
                }
            }
        } catch (TodoListItemNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Todolist or todolist item not found", HttpStatus.NOT_FOUND);
    }
}
