package com.project.todolistbackend.services;

import com.project.todolistbackend.Models.Person;
import com.project.todolistbackend.exceptions.PersonException;
import com.project.todolistbackend.exceptions.PersonNotFoundException;
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
        System.out.println("Ny person id: ");
        System.out.println(entity.getUuid());
        return personRepository.save(entity);
    }

    @Override
    public Collection<Person> findAll() {
        return (Collection<Person>) personRepository.findAll();
    }

    @Override
    public Person findById(UUID id) throws PersonNotFoundException {
        Optional<Person> person = personRepository.findById(id);
        if (!person.isEmpty()) {
            return person.get();
        } else {
            throw new PersonNotFoundException("Person not found");
        }
    }

    @Override
    public Person update(Person entity) throws PersonNotFoundException {
        Person person = findById(entity.getUuid());
        if(person != null) {
            return personRepository.save(entity);
        }
        return null;
    }

    @Override
    public void deleteById(UUID id) throws PersonNotFoundException {
        Person person = findById(id);
        if (person != null) personRepository.deleteById(id);
    }
}
