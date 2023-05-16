package com.project.todolistbackend.services;

import com.project.todolistbackend.exceptions.PersonException;

import java.util.Collection;

public interface CrudService<T, UUID> {
    T add(T entity);
    Collection<T> findAll();
    T findById(UUID id) throws Exception;

    T update(T entity) throws  Exception;

    void deleteById(UUID id) throws Exception;

}
