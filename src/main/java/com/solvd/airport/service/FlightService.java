package com.solvd.airport.service;

import com.solvd.airport.dao.IFlightDAO;
import com.solvd.airport.model.Flight;

import java.util.List;

public class FlightService implements GenericService<Flight>{
    private final IFlightDAO flightDAO;

    public FlightService(IFlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    @Override
    public Flight findByID(Long id) {
        return flightDAO.findByID(id);
    }

    @Override
    public Boolean save(Flight entity) {
        return flightDAO.save(entity);
    }

    @Override
    public Boolean update(Flight entity) {
        return flightDAO.update(entity);
    }

    @Override
    public Boolean delete(Long id) {
        return flightDAO.delete(id);
    }

    @Override
    public List<Flight> findAll() {
        return flightDAO.findAll();
    }
}
