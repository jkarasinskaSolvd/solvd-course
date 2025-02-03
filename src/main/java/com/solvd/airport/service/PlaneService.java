package com.solvd.airport.service;

import com.solvd.airport.dao.IPlaneDAO;
import com.solvd.airport.model.Plane;

import java.util.List;

public class PlaneService implements GenericService<Plane>{
    private final IPlaneDAO planeDAO;

    public PlaneService(IPlaneDAO planeDAO) {
        this.planeDAO = planeDAO;
    }

    @Override
    public Plane findByID(Long id) {
        return planeDAO.findByID(id);
    }

    @Override
    public Boolean save(Plane entity) {
        return planeDAO.save(entity);
    }

    @Override
    public Boolean update(Plane entity) {
        return planeDAO.update(entity);
    }

    @Override
    public Boolean delete(Long id) {
        return planeDAO.delete(id);
    }

    @Override
    public List<Plane> findAll() {
        return planeDAO.findAll();
    }
}
