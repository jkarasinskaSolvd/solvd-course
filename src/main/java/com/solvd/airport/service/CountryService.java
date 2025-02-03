package com.solvd.airport.service;

import com.solvd.airport.dao.ICountryDAO;
import com.solvd.airport.model.Country;

import java.util.List;

public class CountryService implements GenericService<Country>{
    private final ICountryDAO countryDAO;

    public CountryService(ICountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    @Override
    public Country findByID(Long id) {
        return countryDAO.findByID(id);
    }

    @Override
    public Boolean save(Country entity) {
        return countryDAO.save(entity);
    }

    @Override
    public Boolean update(Country entity) {
        return countryDAO.update(entity);
    }

    @Override
    public Boolean delete(Long id) {
        return countryDAO.delete(id);
    }

    @Override
    public List<Country> findAll() {
        return countryDAO.findAll();
    }
}
