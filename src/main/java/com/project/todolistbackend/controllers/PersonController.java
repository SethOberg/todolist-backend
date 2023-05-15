package com.project.todolistbackend.controllers;

import com.project.todolistbackend.Models.Person;
import com.project.todolistbackend.services.PersonServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonServiceImpl personService;

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable UUID id) {
        return personService.findById(id);
    }

    @GetMapping
    public Collection<Person> getAllPeople() {
        return personService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable UUID id) {
        personService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Person updatePerson( @RequestBody Person person) {
        return personService.update(person);
    }

    @PostMapping
    public Person addPerson(@Valid @RequestBody Person person) {
        return personService.add(person);
    }


}
