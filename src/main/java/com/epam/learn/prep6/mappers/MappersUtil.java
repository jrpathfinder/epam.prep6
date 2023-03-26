package com.epam.learn.prep6.mappers;

import com.epam.learn.prep6.model.Event;
import com.epam.learn.prep6.model.Ticket;
import com.epam.learn.prep6.model.TicketCategory;
import com.epam.learn.prep6.model.User;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MappersUtil {
    public Map<String, Ticket> toTickets(List<LinkedHashMap> tickets) {
        return tickets
                .stream()
                .map(this::toTicket)
                .collect(Collectors.toMap(t -> "ticket:" + t.getId(), t -> t));
    }

    public Ticket toTicket(LinkedHashMap el) {
        return Ticket.builder()
                .id(Long.valueOf((Integer) el.get("id")))
                .category(TicketCategory.BAR)
                .event(toEvent((LinkedHashMap) el.get("event")))
                .user(toUser((LinkedHashMap) el.get("user")))
                .place((Integer) el.get("place"))
                .build();
    }

    public Map<String, User> toUsers(List<LinkedHashMap> users) {
        return users
                .stream()
                .map(this::toUser)
                .collect(Collectors.toMap(u -> "user:" + u.getId(), u -> u));
    }

    public User toUser(LinkedHashMap el) {
        return User.builder()
                .id(Long.valueOf((Integer) el.get("id")))
                .name((String) el.get("name"))
                .email((String) el.get("email"))
                .build();
    }

    public Event toEvent(LinkedHashMap el){
        OffsetDateTime date = OffsetDateTime.parse((String) el.get("date"),
                DateTimeFormatter.ISO_DATE_TIME);
        return Event.builder()
                .id(Long.valueOf((Integer) el.get("id")))
                .title((String) el.get("title"))
                .date(date)
                .build();
    }

    public Map<String, Event> toEvents(List<LinkedHashMap> events) {
        return events
                .stream()
                .map(this::toEvent)
                .collect(Collectors.toMap(e -> "event:" +e.getId(), e -> e));
    }
}
