package com.project.todolistbackend.controllers;

import com.project.todolistbackend.Models.Person;
import com.project.todolistbackend.Models.TodoList;
import com.project.todolistbackend.exceptions.PersonException;
import com.project.todolistbackend.exceptions.PersonNotFoundException;
import com.project.todolistbackend.services.PersonServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServiceImpl personService;

    @GetMapping("/{id}")
    public ResponseEntity getPerson(@PathVariable UUID id) {
        try {
            return new ResponseEntity(personService.findById(id), HttpStatus.OK);
        } catch (PersonNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public Collection<Person> getAllPeople() {
        return personService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePerson(@PathVariable UUID id)  {
        try {
            personService.deleteById(id);
            return new ResponseEntity("Person deleted", HttpStatus.OK);
        } catch (PersonNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public Person updatePerson( @RequestBody Person person) {
        return personService.update(person);
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
