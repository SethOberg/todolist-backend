package com.project.todolistbackend.services;

import com.project.todolistbackend.Models.Person;
import com.project.todolistbackend.exceptions.PersonException;

import java.util.UUID;

public interface PersonService extends CrudService<Person, UUID> {
}
