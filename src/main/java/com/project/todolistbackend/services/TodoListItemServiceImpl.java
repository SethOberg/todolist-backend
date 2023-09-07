package com.project.todolistbackend.services;

import com.project.todolistbackend.Models.TodoListItem;
import com.project.todolistbackend.exceptions.TodoListItemNotFoundException;
import com.project.todolistbackend.repositories.TodoListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoListItemServiceImpl implements TodoListItemService {

    @Autowired
    private TodoListItemRepository todoListItemRepository;

    @Override
    public TodoListItem add(TodoListItem entity) {
        return todoListItemRepository.save(entity);
    }

    @Override
    public Collection<TodoListItem> findAll() {
        return (Collection<TodoListItem>) todoListItemRepository.findAll();
    }

    @Override
    public TodoListItem findById(UUID id) throws TodoListItemNotFoundException {
        Optional<TodoListItem> todoListItem = todoListItemRepository.findById(id);

        if(todoListItem.isPresent()) {
            return todoListItem.get();
        } else {
            throw new TodoListItemNotFoundException("Todolist item not found");
        }

    }

    @Override
    public TodoListItem update(TodoListItem entity) {
        return todoListItemRepository.save(entity);
    }

    @Override
    public void deleteById(UUID id) throws TodoListItemNotFoundException {
        TodoListItem todoListItem = findById(id);
        if(todoListItem != null) {
            todoListItemRepository.deleteById(id);
        }
    }
}
