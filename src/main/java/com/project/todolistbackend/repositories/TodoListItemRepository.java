package com.project.todolistbackend.repositories;

import com.project.todolistbackend.Models.TodoListItem;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TodoListItemRepository extends CrudRepository<TodoListItem, UUID> {
}
