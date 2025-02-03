package com.solvd.airport.service;

import com.solvd.airport.dao.IPlaneTypeDAO;
import com.solvd.airport.model.PlaneType;

import java.util.List;

public class PlaneTypeService implements GenericService<PlaneType>{
    private final IPlaneTypeDAO planeTypeDAO;

    public PlaneTypeService(IPlaneTypeDAO planeTypeDAO) {
        this.planeTypeDAO = planeTypeDAO;
    }

    @Override
    public PlaneType findByID(Long id) {
        return planeTypeDAO.findByID(id);
    }

    @Override
    public Boolean save(PlaneType entity) {
        return planeTypeDAO.save(entity);
    }

    @Override
    public Boolean update(PlaneType entity) {
        return planeTypeDAO.update(entity);
    }

    @Override
    public Boolean delete(Long id) {
        return planeTypeDAO.delete(id);
    }

    @Override
    public List<PlaneType> findAll() {
        return planeTypeDAO.findAll();
    }
}
