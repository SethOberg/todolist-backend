package com.project.todolistbackend.Models;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String name;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<TodoList> todoLists = new LinkedList<>();

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
