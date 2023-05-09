package com.project.todolistbackend.repositories;

import com.project.todolistbackend.Models.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonRepository extends CrudRepository<Person, UUID> {

}
