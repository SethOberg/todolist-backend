package com.project.todolistbackend.services;

import com.project.todolistbackend.Models.TodoList;
import com.project.todolistbackend.repositories.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class TodoListServiceImpl implements TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    @Override
    public TodoList add(TodoList entity) {
        return null;
    }

    @Override
    public Collection<TodoList> findAll() {
        return (Collection<TodoList>) todoListRepository.findAll();
    }

    @Override
    public TodoList findById(UUID id) {
        return null;
    }

    @Override
    public TodoList update(TodoList entity) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
