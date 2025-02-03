package com.solvd.airport.service;

import com.solvd.airport.dao.IPersonDAO;
import com.solvd.airport.model.Person;

import java.util.List;

public class PersonService implements GenericService<Person> {
    private final IPersonDAO personDAO;

    public PersonService(IPersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public Person findByID(Long id) {
        return personDAO.findByID(id);
    }

    @Override
    public Boolean save(Person entity) {
        return personDAO.save(entity);
    }

    @Override
    public Boolean update(Person entity) {
        return personDAO.update(entity);
    }

    @Override
    public Boolean delete(Long id) {
        return personDAO.delete(id);
    }

    @Override
    public List<Person> findAll() {
        return personDAO.findAll();
    }
}
