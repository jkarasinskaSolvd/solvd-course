package com.solvd.airport.service;

import com.solvd.airport.dao.ITicketDAO;
import com.solvd.airport.model.Ticket;

import java.util.List;

public class TicketDAO implements GenericService<Ticket> {
    private final ITicketDAO ticketDAO;

    public TicketDAO(ITicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Override
    public Ticket findByID(Long id) {
        return ticketDAO.findByID(id);
    }

    @Override
    public Boolean save(Ticket entity) {
        return ticketDAO.save(entity);
    }

    @Override
    public Boolean update(Ticket entity) {
        return ticketDAO.update(entity);
    }

    @Override
    public Boolean delete(Long id) {
        return ticketDAO.delete(id);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketDAO.findAll();
    }
}
