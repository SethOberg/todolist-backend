package com.project.todolistbackend.controllers;

import com.project.todolistbackend.Models.Person;
import com.project.todolistbackend.Models.TodoList;
import com.project.todolistbackend.exceptions.PersonException;
import com.project.todolistbackend.exceptions.PersonNotFoundException;
import com.project.todolistbackend.services.PersonServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServiceImpl personService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person found",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Person.class))}),
            @ApiResponse(responseCode = "404", description = "Person not found",
            content = @Content)
    })
    @Operation(summary = "Get a person by their id")
    @GetMapping("/{id}")
    public ResponseEntity getPerson(@PathVariable UUID id) {
        try {
            return new ResponseEntity(personService.findById(id), HttpStatus.OK);
        } catch (PersonNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))
            }),
            @ApiResponse(responseCode = "404", description = "Person not found",
                    content = @Content)
    })
    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity getAllPeople() {
        List<Person> people = (List<Person>) personService.findAll();
        if(people.isEmpty()) {
            return new ResponseEntity("No users in database", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(people, HttpStatus.OK);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))
            }),
            @ApiResponse(responseCode = "404", description = "Person not found",
                    content = @Content)
    })
    @Operation(summary = "Delete person by id")
    @DeleteMapping("/{id}")
    public ResponseEntity deletePerson(@PathVariable UUID id)  {
        try {
            personService.deleteById(id);
            return new ResponseEntity("Person deleted", HttpStatus.OK);
        } catch (PersonNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class))}),
            @ApiResponse(responseCode = "404", description = "Person not found",
                    content = @Content)
    })
    @Operation(summary = "Update a person with the id defined in the requestbody")
    @PutMapping("/{id}")
    public ResponseEntity updatePerson(@RequestBody Person person) {
        try {
            return new ResponseEntity<>(personService.update(person), HttpStatus.OK);
        } catch (PersonNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Person addPerson(@Valid @RequestBody Person person) {
        return personService.add(person);
    }

    @PutMapping(path = "addToDoList/{id}")
    public void addTodoList(@PathVariable UUID id, @RequestBody TodoList todoList) throws Exception {
        Person person = personService.findById(id);
        todoList.setPerson(person);
        if(person != null) {
            person.addTodoList(todoList);
        }
        personService.update(person);
    }

}
