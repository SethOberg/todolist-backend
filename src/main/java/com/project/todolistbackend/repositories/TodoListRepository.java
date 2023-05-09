package com.project.todolistbackend.repositories;

import com.project.todolistbackend.Models.TodoList;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TodoListRepository extends CrudRepository<TodoList, UUID> {
}
