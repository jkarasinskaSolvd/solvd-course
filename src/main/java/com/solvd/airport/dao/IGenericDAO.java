package com.solvd.airport.dao;

import java.util.List;

public interface IGenericDAO<T>{
    T findByID(Long id);

    Boolean save (T entity);

    Boolean update(T entity);

    Boolean delete(Long id);

    List<T> findAll();
}
