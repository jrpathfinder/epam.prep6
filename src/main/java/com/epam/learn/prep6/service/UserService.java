package com.epam.learn.prep6.service;

import com.epam.learn.prep6.dao.UserDao;
import com.epam.learn.prep6.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserDao dao;

    public User get(long eventId) {
        return dao.get(eventId);
    }
    public User getByEmail(String email) {
        return dao.get(email);
    }

    public List<User> getByNames(String name) {
        return dao.getByName(name);
    }

    public User saveOrUpdate(User user) {
        return dao.saveOrUpdate(user);
    }

    public boolean deleteUser(long userId) {
        return dao.delete(userId);
    }
}
