package com.epam.learn.prep6;

import com.epam.learn.prep6.model.Event;
import com.epam.learn.prep6.model.Ticket;
import com.epam.learn.prep6.model.TicketCategory;
import com.epam.learn.prep6.model.User;
import com.epam.learn.prep6.service.EventService;
import com.epam.learn.prep6.service.TicketService;
import com.epam.learn.prep6.service.UserService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingFacade {
    private final EventService eventService;
    private final UserService userService;
    private final TicketService ticketService;

    public Event getEventById(long eventId) {
        return eventService.get(eventId);
    }

    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventService.get(title);
    }

    public List<Event> getEventsForDay(OffsetDateTime day, int pageSize, int pageNum) {
        return eventService.get(day);
    }

    public Event createEvent(Event event) {
        return eventService.saveOrUpdate(event);
    }

    public Event updateEvent(Event event) {
        return eventService.saveOrUpdate(event);
    }

    public boolean deleteEvent(long eventId) {
        return eventService.deleteEvent(eventId);
    }

    public User getUserById(long userId) {
        return userService.get(userId);
    }

    public User getUserByEmail(String email) {
        return userService.getByEmail(email);
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userService.getByNames(name);
    }

    public User createUser(User user) {
        return userService.saveOrUpdate(user);
    }

    public User updateUser(User user) {
        return userService.saveOrUpdate(user);
    }

    public boolean deleteUser(long userId) {
        return userService.deleteUser(userId);
    }

    public Ticket bookTicket(long userId, long eventId, int place, TicketCategory category) {
        return ticketService.bookTicket(userId, eventId, place, category);
    }

    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketService.getByUser(user);
    }

    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketService.getByEvent(event);
    }

    public boolean cancelTicket(long ticketId) {
        return ticketService.cancel(ticketId);
    }

    public void preloadTickets() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        List<Ticket> value = xmlMapper.readValue(new File("tickets.xml"), List.class);
        for (Ticket t : value) {
            ticketService.bookTicket(t.getUser().getId(), t.getEvent().getId(), t.getPlace(), t.getCategory());
        }
        System.out.println(value);
    }
}

