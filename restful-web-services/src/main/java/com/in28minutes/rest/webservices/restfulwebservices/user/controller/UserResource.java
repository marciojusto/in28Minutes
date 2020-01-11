package com.in28minutes.rest.webservices.restfulwebservices.user.controller;

import com.in28minutes.rest.webservices.restfulwebservices.user.entities.User;
import com.in28minutes.rest.webservices.restfulwebservices.user.exceptions.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.user.services.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private final UserDAOService service;

    @Autowired
    public UserResource(UserDAOService service) {
        this.service = service;
    }

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User retrieveAllUsers(@PathVariable long id) {
        User user = service.findOne(id);
        if(user == null) {
            throw new UserNotFoundException(String.format("id-%s", id));
        }
        return user;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User userCreated = service.save(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
