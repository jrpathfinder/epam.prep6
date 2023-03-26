package com.epam.learn.prep6.model;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by maksym_govorischev.
 */
@Data
@Builder
@XmlRootElement(name = "ticket")
public class Ticket {
    /**
     * com.epam.learn.assign1.com.epam.learn.prep6.model.Ticket Id. UNIQUE.
     * @return com.epam.learn.assign1.com.epam.learn.prep6.model.Ticket Id.
     */
    Long id;
    Event event;
    User user;
    TicketCategory category;
    Integer place;
    Boolean booked;

}
