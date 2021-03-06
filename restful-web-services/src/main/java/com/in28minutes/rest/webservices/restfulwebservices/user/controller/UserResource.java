package com.in28minutes.rest.webservices.restfulwebservices.user.controller;

import com.in28minutes.rest.webservices.restfulwebservices.user.entities.User;
import com.in28minutes.rest.webservices.restfulwebservices.user.exceptions.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.user.services.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
    public Resource<User> retrieveUser(@PathVariable long id) {
        User user = service.findOne(id);
        if(user == null) {
            throw new UserNotFoundException(String.format("id-%s", id));
        }

        //HATEOAS [Hypermedia as the Engine of Application State]
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteById(@PathVariable long id) {
        User user = service.deleteById(id);
        if(user == null) {
            throw new UserNotFoundException(String.format("id-%s", id));
        }
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User userCreated = service.save(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
