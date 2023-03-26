package com.epam.learn.prep6.service;

import com.epam.learn.prep6.dao.TicketDao;
import com.epam.learn.prep6.model.Event;
import com.epam.learn.prep6.model.Ticket;
import com.epam.learn.prep6.model.TicketCategory;
import com.epam.learn.prep6.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketDao dao;

    public Ticket bookTicket(long userId, long eventId, int place, TicketCategory category) {
        Ticket ticket = dao.find(userId, eventId, place, category);
        ticket.setBooked(true);
        return dao.saveOrUpdate(ticket);
    }

    public List<Ticket> getByUser(User user) {
        return dao.get(user);
    }

    public List<Ticket> getByEvent(Event event) {
        return dao.get(event);
    }

    public boolean cancel(long id) {
        Ticket t = dao.get(id);
        t.setBooked(false);
        return dao.saveOrUpdate(t) != null;
    }
}
