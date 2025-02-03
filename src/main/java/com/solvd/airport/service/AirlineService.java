package com.solvd.airport.service;

import com.solvd.airport.dao.IAirlineDAO;
import com.solvd.airport.model.Airline;

import java.util.List;

public class AirlineService implements GenericService<Airline> {
    private final IAirlineDAO airlineDAO;

    public AirlineService(IAirlineDAO airlineDAO) {
        this.airlineDAO = airlineDAO;
    }

    @Override
    public Airline findByID(Long id) {
        return airlineDAO.findByID(id);
    }

    @Override
    public Boolean save(Airline entity) {
        return airlineDAO.save(entity);
    }

    @Override
    public Boolean update(Airline entity) {
        return airlineDAO.update(entity);
    }

    @Override
    public Boolean delete(Long id) {
        return airlineDAO.delete(id);
    }

    @Override
    public List<Airline> findAll() {
        return airlineDAO.findAll();
    }
}
