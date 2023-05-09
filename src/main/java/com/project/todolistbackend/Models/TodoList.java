package com.project.todolistbackend.Models;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.UUID;

@Entity
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private boolean completed;

    private String name;

    @ManyToOne
    @JoinColumn(name="person_id", nullable = false)
    private Person person;

    @OneToMany(mappedBy = "todoList")
    private LinkedList<TodoListItem> todoListItems = new LinkedList<>();

    public TodoList() {
    }

    public TodoList(boolean completed, String name) {
        this.completed = completed;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
