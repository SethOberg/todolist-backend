package com.project.todolistbackend.services;

import com.project.todolistbackend.Models.Person;
import com.project.todolistbackend.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Override
    public Person add(Person entity) {
        return personRepository.save(entity);
    }

    @Override
    public Collection<Person> findAll() {
        return (Collection<Person>) personRepository.findAll();
    }

    @Override
    public Person findById(UUID id) {
        Optional<Person> person = personRepository.findById(id);
        return !person.isEmpty() ? person.get() : null;
    }

    @Override
    public Person update(Person entity) {
        return personRepository.save(entity);
    }

    @Override
    public void deleteById(UUID id) {
        Person person = findById(id);
        if (person != null) personRepository.deleteById(id);
    }
}
