package com.epam.learn.prep6.controller;

import com.epam.learn.prep6.BookingFacade;
import com.epam.learn.prep6.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    BookingFacade facade;

    @GetMapping("/user/id")
    public String getUserById(Model model, @RequestParam("id") Integer id){
        User user = facade.getUserById(id);
        model.addAttribute("user", user);
        return "user.html";
    }

    @GetMapping("/user/email")
    public String getUserByEmail(Model model, @RequestParam("email") String email){
        User user = facade.getUserByEmail(email);
        model.addAttribute("user", user);
        return "user.html";
    }

    @GetMapping("/user/name")
    public String getUsersByName(Model model, @RequestParam("name") String name,
                                 @RequestParam("size") Optional<Integer> size,
                                 @RequestParam("num") Optional<Integer> num){
        final int currentNum = num.orElse(1);
        final int pageSize = size.orElse(5);
        List<User> users = facade.getUsersByName(name, pageSize, currentNum);
        model.addAttribute("users", users);
        return "users.html";
    }

    @GetMapping("/user/pdf")
    public ModelAndView getUsersByName(ModelAndView modelAndView, @RequestHeader(HttpHeaders.ACCEPT) String accept,
                                           @RequestParam("name") String name,
                                           @RequestParam("size") Optional<Integer> size,
                                           @RequestParam("num") Optional<Integer> num){
        final int currentNum = num.orElse(1);
        final int pageSize = size.orElse(5);
        List<User> users = facade.getUsersByName(name, pageSize, currentNum);
        return new ModelAndView("pdfView", "listUsers", users);
    }

    @PostMapping("/user")
    public void createUser(@ModelAttribute User user){
        facade.createUser(user);
    }

    @PutMapping("/user")
    public void updateUser(@ModelAttribute User user){
        facade.updateUser(user);
    }

    @DeleteMapping("/user")
    public void delete(@RequestParam("id") Integer id){
        facade.deleteUser(id);
    }
}
