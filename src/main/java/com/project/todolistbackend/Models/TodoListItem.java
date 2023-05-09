package com.project.todolistbackend.Models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class TodoListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String todoDescription;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name="todoList_id", nullable = false)
    private TodoList todoList;

    public TodoListItem() {
    }

    public TodoListItem(String todoDescription, boolean completed) {
        this.todoDescription = todoDescription;
        this.completed = completed;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTodoDescription() {
        return todoDescription;
    }

    public void setTodoDescription(String todoDescription) {
        this.todoDescription = todoDescription;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
