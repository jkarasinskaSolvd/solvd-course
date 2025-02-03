package com.solvd.airport.service;

import com.solvd.airport.dao.IHangarDAO;
import com.solvd.airport.model.Hangar;

import java.util.List;

public class HangarService implements GenericService<Hangar>{
    private final IHangarDAO hangarDAO;

    public HangarService(IHangarDAO hangarDAO) {
        this.hangarDAO = hangarDAO;
    }

    @Override
    public Hangar findByID(Long id) {
        return hangarDAO.findByID(id);
    }

    @Override
    public Boolean save(Hangar entity) {
        return hangarDAO.save(entity);
    }

    @Override
    public Boolean update(Hangar entity) {
        return hangarDAO.update(entity);
    }

    @Override
    public Boolean delete(Long id) {
        return hangarDAO.delete(id);
    }

    @Override
    public List<Hangar> findAll() {
        return hangarDAO.findAll();
    }
}
