package com.solvd.airport.service;

import com.solvd.airport.dao.ITicketClassDAO;
import com.solvd.airport.model.TicketClass;

import java.util.List;

public class TicketClassService implements GenericService<TicketClass> {
    private final ITicketClassDAO ticketClassDAO;

    public TicketClassService(ITicketClassDAO ticketClassDAO) {
        this.ticketClassDAO = ticketClassDAO;
    }

    @Override
    public TicketClass findByID(Long id) {
        return ticketClassDAO.findByID(id);
    }

    @Override
    public Boolean save(TicketClass entity) {
        return ticketClassDAO.save(entity);
    }

    @Override
    public Boolean update(TicketClass entity) {
        return ticketClassDAO.update(entity);
    }

    @Override
    public Boolean delete(Long id) {
        return ticketClassDAO.delete(id);
    }

    @Override
    public List<TicketClass> findAll() {
        return ticketClassDAO.findAll();
    }
}
