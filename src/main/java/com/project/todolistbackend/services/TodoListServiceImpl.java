package com.project.todolistbackend.services;

import com.project.todolistbackend.Models.TodoList;
import com.project.todolistbackend.Models.TodoListItem;
import com.project.todolistbackend.exceptions.TodoListNotFoundException;
import com.project.todolistbackend.repositories.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoListServiceImpl implements TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    @Override
    public TodoList add(TodoList entity) {
        return todoListRepository.save(entity);
    }

    @Override
    public Collection<TodoList> findAll() {
        return (Collection<TodoList>) todoListRepository.findAll();
    }

    @Override
    public TodoList findById(UUID id) throws TodoListNotFoundException {
        Optional<TodoList> todoList = todoListRepository.findById(id);

        if(todoList != null) {
            return todoList.get();
        } else {
            throw new TodoListNotFoundException("Todolist not found");
        }
    }

    @Override
    public TodoList update(TodoList entity) throws TodoListNotFoundException {
        TodoList todoList = findById(entity.getId());
        return todoListRepository.save(entity);
    }

    @Override
    public void deleteById(UUID id) throws TodoListNotFoundException {
        TodoList todoList = findById(id);

        if(todoList != null) {
            todoListRepository.deleteById(id);
        }
    }
}
