package com.epam.learn.prep6.dao;

import com.epam.learn.prep6.model.User;
import com.epam.learn.prep6.storage.InMemoryStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final InMemoryStorage storage;

    public User get(long userId) {
        return storage
                .getUsers().get("user:" + userId);
    }

    public User get(String email) {
        return storage.getUsers()
                .values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny().get();
    }

    public List<User> getByName(String name) {
        return storage.getUsers()
                .values().stream()
                .filter(user -> user.getName().equals(name))
                .collect(Collectors.toList());
    }

    public User saveOrUpdate(User user) {
        return storage.getUsers().put("user:" + user.getId(), user);
    }

    public boolean delete(long userId) {
        return storage.getUsers().remove("user:" + userId) != null;
    }
}
