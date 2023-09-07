package com.project.todolistbackend.repositories;

import com.project.todolistbackend.Models.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PersonRepository extends CrudRepository<Person, UUID> {

    @Query(value = "SELECT * FROM person WHERE name = :name LIMIT 1", nativeQuery = true)
    Person findByName(@Param("name") String name);

}
