package com.epam.learn.prep6.service;

import com.epam.learn.prep6.dao.EventDAO;
import com.epam.learn.prep6.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventDAO dao;

    public Event get(long eventId) {
        return dao.get(eventId);
    }
    public List<Event> get(String title) {
        return dao.get(title);
    }

    public List<Event> get(OffsetDateTime date) {
        return dao.get(date);
    }

    public Event saveOrUpdate(Event event) {
        return dao.saveOrUpdate(event);
    }

    public boolean deleteEvent(long eventId) {
      return dao.delete(eventId);
    }
}
