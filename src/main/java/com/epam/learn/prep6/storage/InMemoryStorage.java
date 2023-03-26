package com.epam.learn.prep6.storage;

import com.epam.learn.prep6.mappers.MappersUtil;
import com.epam.learn.prep6.model.Event;
import com.epam.learn.prep6.model.Ticket;
import com.epam.learn.prep6.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class InMemoryStorage {
    private static Map<String, List> data = new HashMap<>();
    @Getter
    private Map<String, Event> events = new HashMap<>();
    @Getter
    private Map<String, User> users = new HashMap<>();
    @Getter
    public Map<String, Ticket> tickets = new HashMap<>();
    @Value("data.json")
    private Resource storageDataFile;
    private ObjectMapper objectMapper = new ObjectMapper();
    private MappersUtil mapper = new MappersUtil();
    @PostConstruct
    void setUp() throws IOException {
        File file = storageDataFile.getFile();
        data = objectMapper.readValue(file, Map.class);
        events = mapper.toEvents(data.get("events"));
        users = mapper.toUsers(data.get("users"));
        tickets = mapper.toTickets(data.get("tickets"));
        log.info("Events: {}", events);
        log.info("Users: {}", users);
        log.info("Tickets: {}", tickets);
    }
}
