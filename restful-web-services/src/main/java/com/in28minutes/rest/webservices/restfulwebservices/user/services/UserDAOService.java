package com.in28minutes.rest.webservices.restfulwebservices.user.services;

import com.in28minutes.rest.webservices.restfulwebservices.user.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDAOService {

    private static List<User> users = new ArrayList<>();

    private long usersCount = 3;

    static {
        users.add(new User(1l, "Adam", new Date()));
        users.add(new User(2l, "Eve", new Date()));
        users.add(new User(3l, "Jack", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(Long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
