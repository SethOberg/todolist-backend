package com.project.todolistbackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;

@Entity
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private boolean completed;

    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="person_id", nullable = false)
    private Person person;

    @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL)
    private Set<TodoListItem> todoListItems = new HashSet<>();

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }



    public void addTodoListItem(TodoListItem todoListItem) {
        todoListItems.add(todoListItem);
    }


}
