package com.in28minutes.rest.webservices.restfulwebservices.user.controller;

import com.in28minutes.rest.webservices.restfulwebservices.user.entities.Post;
import com.in28minutes.rest.webservices.restfulwebservices.user.entities.User;
import com.in28minutes.rest.webservices.restfulwebservices.user.exceptions.UserNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    private final UserRepository repository;

    @Autowired
    public UserJPAResource(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public Resource<User> retrieveUser(@PathVariable long id) {
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException(String.format("id-%s", id));
        }

        //HATEOAS [Hypermedia as the Engine of Application State]
        Resource<User> resource = new Resource<User>(user.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteById(@PathVariable long id) {
        repository.deleteById(id);
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User userCreated = repository.save(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable long id) {
        Optional<User> userOptional = repository.findById(id);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("id-"+id);
        }
        return userOptional.get().getPosts();
    }

}
