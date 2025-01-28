package com.solvd.airport.dao;

import java.util.List;

public interface GenericDAO <T>{
    T findByID(Long id);

    Boolean save (T entity);

    Boolean update(T entity);

    Boolean delete(Long id);

    List<T> findAll();
}
