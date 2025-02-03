package com.solvd.airport.service;

import com.solvd.airport.dao.IAirportDAO;
import com.solvd.airport.model.Airport;

import java.util.List;

public class AirportService implements GenericService<Airport> {
    private final IAirportDAO airportDAO;
    public AirportService(IAirportDAO airportDAO) {
        this.airportDAO = airportDAO;
    }

    @Override
    public Airport findByID(Long id) {
        return airportDAO.findByID(id);
    }

    @Override
    public Boolean save(Airport entity) {
        return airportDAO.save(entity);
    }

    @Override
    public Boolean update(Airport entity) {
        return airportDAO.update(entity);
    }

    @Override
    public Boolean delete(Long id) {
        return airportDAO.delete(id);
    }

    @Override
    public List<Airport> findAll() {
        return airportDAO.findAll();
    }
}
