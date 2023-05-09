package com.project.todolistbackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<TodoList> todoLists = new HashSet<>();

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

    public Set<TodoList> getTodoLists() {
        return todoLists;
    }

    public void setTodoLists(Set<TodoList> todoLists) {
        this.todoLists = todoLists;
    }

    public void addTodoList(TodoList todoList) {
        todoLists.add(todoList);
    }
}
