package com.epam.learn.prep6.dao;

import com.epam.learn.prep6.model.Event;
import com.epam.learn.prep6.model.Ticket;
import com.epam.learn.prep6.model.TicketCategory;
import com.epam.learn.prep6.model.User;
import com.epam.learn.prep6.storage.InMemoryStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TicketDao {
    private final InMemoryStorage storage;

    public Ticket find(long userId, long eventId, int place, TicketCategory category) {
        return storage.getTickets()
                .values().stream()
                .filter(ticket -> ticket.getEvent().getId() == eventId && ticket.getUser().getId() == userId &&
                        ticket.getPlace() == place && ticket.getCategory() == category)
                .findAny().get();
    }

    public Ticket saveOrUpdate(Ticket ticket) {
        return storage.getTickets().put("ticket:" + ticket.getId(), ticket);
    }

    public List<Ticket> get(User user) {
        return storage.getTickets().values().stream()
                .filter(t -> t.getUser().getId().equals(user.getId()) && t.getBooked())
                .collect(Collectors.toList());
    }

    public List<Ticket> get(Event event) {
        return storage.getTickets().values().stream()
                .filter(ticket -> ticket.getEvent().getId().equals(event.getId()) && ticket.getBooked())
                .collect(Collectors.toList());
    }

    public Ticket get(long id) {
        return storage.getTickets().values().stream()
                .filter(ticket -> ticket.getId().equals(id) && ticket.getBooked())
                .findAny().get();
    }
}
