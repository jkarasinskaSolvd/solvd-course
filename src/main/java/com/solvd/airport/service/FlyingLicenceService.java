package com.solvd.airport.service;

import com.solvd.airport.dao.IFlyingLicenceDAO;
import com.solvd.airport.model.FlyingLicence;

import java.util.List;

public class FlyingLicenceService implements GenericService<FlyingLicence>{
    private final IFlyingLicenceDAO flyingLicenceDAO;

    public FlyingLicenceService(IFlyingLicenceDAO flyingLicenceDAO) {
        this.flyingLicenceDAO = flyingLicenceDAO;
    }

    @Override
    public FlyingLicence findByID(Long id) {
        return flyingLicenceDAO.findByID(id);
    }

    @Override
    public Boolean save(FlyingLicence entity) {
        return flyingLicenceDAO.save(entity);
    }

    @Override
    public Boolean update(FlyingLicence entity) {
        return flyingLicenceDAO.update(entity);
    }

    @Override
    public Boolean delete(Long id) {
        return flyingLicenceDAO.delete(id);
    }

    @Override
    public List<FlyingLicence> findAll() {
        return flyingLicenceDAO.findAll();
    }
}
