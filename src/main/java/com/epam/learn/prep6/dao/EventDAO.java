package com.epam.learn.prep6.dao;

import com.epam.learn.prep6.model.Event;
import com.epam.learn.prep6.storage.InMemoryStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventDAO {

    private final InMemoryStorage storage;

    public Event get(long eventId) {
        return storage
                .getEvents()
                .get("event:" + eventId);
    }

    public List<Event> get(String title) {
        return storage
                .getEvents()
                .values()
                .stream()
                .filter(event -> event.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    public List<Event> get(OffsetDateTime date) {
        return storage.getEvents()
                .values().stream()
                .filter(event -> date.toLocalDate().equals(event.getDate().toLocalDate()))
                .collect(Collectors.toList());
    }

    public Event saveOrUpdate(Event event) {
        return storage.getEvents().put("event:" + event.getId(), event);
    }

    public boolean delete(long eventId) {
        return storage.getEvents().remove("event:" + eventId) != null;
    }
}
