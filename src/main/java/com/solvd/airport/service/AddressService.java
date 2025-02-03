package com.solvd.airport.service;

import com.solvd.airport.dao.IAddressDAO;
import com.solvd.airport.model.Address;

import java.util.List;

public class AddressService implements GenericService<Address>{
    private final IAddressDAO addressDAO;
    public AddressService(IAddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }
    @Override
    public Address findByID(Long id){
        return addressDAO.findByID(id);
    }

    @Override
    public Boolean save (Address entity){
        return addressDAO.save(entity);
    }

    @Override
    public Boolean update(Address entity){
        return addressDAO.update(entity);
    }

    @Override
    public Boolean delete(Long id){
        return addressDAO.delete(id);
    }

    @Override
    public List<Address> findAll(){
        return addressDAO.findAll();
    }
}
