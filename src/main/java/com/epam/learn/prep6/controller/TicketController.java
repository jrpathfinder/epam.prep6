package com.epam.learn.prep6.controller;

import com.epam.learn.prep6.BookingFacade;
import com.epam.learn.prep6.model.Event;
import com.epam.learn.prep6.model.Ticket;
import com.epam.learn.prep6.model.TicketCategory;
import com.epam.learn.prep6.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class TicketController {
    @Autowired
    BookingFacade facade;

    @GetMapping("/ticket")
    public String bookTicket(Model model, @RequestParam("userId") Integer userId, @RequestParam("eventId") Integer eventId,
                             @RequestParam("place") Integer place, @RequestParam("category") TicketCategory category ){
        Ticket ticket = facade.bookTicket(userId, eventId, place, category);
        model.addAttribute("ticket", ticket);
        return "ticket.html";
    }

    @RequestMapping(value = "/ticket/user", method = RequestMethod.GET)
    public String getBookedTickets(Model model, @ModelAttribute Event event,
                                 @RequestParam("size") Optional<Integer> size,
                                 @RequestParam("num") Optional<Integer> num){
        final int currentNum = num.orElse(1);
        final int pageSize = size.orElse(5);
        List<Ticket> tickets = facade.getBookedTickets(event, pageSize, currentNum);
        model.addAttribute("tickets", tickets);
        return "tickets.html";
    }

    @RequestMapping(value = "/ticket/event", method = RequestMethod.GET)
    public String getBookedTickets(Model model, @ModelAttribute User user,
                                   @RequestParam("size") Optional<Integer> size,
                                   @RequestParam("num") Optional<Integer> num){
        final int currentNum = num.orElse(1);
        final int pageSize = size.orElse(5);
        List<Ticket> tickets = facade.getBookedTickets(user, pageSize, currentNum);
        model.addAttribute("tickets", tickets);
        return "tickets.html";
    }

    @RequestMapping(value = "/ticket", method = RequestMethod.PUT)
    public void cancel(@RequestParam("id") Integer id){
        facade.cancelTicket(id);
    }

    @GetMapping(name = "/preload")
    public void preload() throws IOException {
        facade.preloadTickets();
    }

}
