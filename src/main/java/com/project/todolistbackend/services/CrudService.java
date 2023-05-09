package com.project.todolistbackend.services;

import java.util.Collection;

public interface CrudService<T, UUID> {
    T add(T entity);
    Collection<T> findAll();
    T findById(UUID id);

    T update(T entity);

    void deleteById(UUID id);

}
