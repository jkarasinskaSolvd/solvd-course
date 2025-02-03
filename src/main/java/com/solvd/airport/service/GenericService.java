package com.solvd.airport.service;

import java.util.List;

public interface GenericService <T>{
    T findByID(Long id);

    Boolean save (T entity);

    Boolean update(T entity);

    Boolean delete(Long id);

    List<T> findAll();
}
