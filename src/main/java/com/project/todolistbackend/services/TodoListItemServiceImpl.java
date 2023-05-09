package com.project.todolistbackend.services;

import com.project.todolistbackend.Models.TodoListItem;
import com.project.todolistbackend.repositories.TodoListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class TodoListItemServiceImpl implements TodoListItemService {

    @Autowired
    private TodoListItemRepository todoListItemRepository;

    @Override
    public TodoListItem add(TodoListItem entity) {
        return null;
    }

    @Override
    public Collection<TodoListItem> findAll() {
        return (Collection<TodoListItem>) todoListItemRepository.findAll();
    }

    @Override
    public TodoListItem findById(UUID id) {
        return null;
    }

    @Override
    public TodoListItem update(TodoListItem entity) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
