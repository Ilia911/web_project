package com.epam.jwd.web.dao;


import java.util.List;
import java.util.Optional;

public interface CommonDao<T> {

    Optional<List<T>> findAll();

    Optional<T> register(T entity);

    Optional<T> findById(int id);

    Optional<T> update(T entity);

    boolean remove(int id);

}
