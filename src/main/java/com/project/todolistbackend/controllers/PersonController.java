package com.project.todolistbackend.controllers;

import com.project.todolistbackend.Models.Person;
import com.project.todolistbackend.services.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
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


}
