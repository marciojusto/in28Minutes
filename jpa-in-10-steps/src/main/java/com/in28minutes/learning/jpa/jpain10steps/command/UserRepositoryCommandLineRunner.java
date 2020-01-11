package com.in28minutes.learning.jpa.jpain10steps.command;

import com.in28minutes.learning.jpa.jpain10steps.entity.User;
import com.in28minutes.learning.jpa.jpain10steps.service.UserDAOService;
import com.in28minutes.learning.jpa.jpain10steps.service.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);

    private final UserRepository repository;

    @Autowired
    public UserRepositoryCommandLineRunner(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("John", "Admin");
        repository.save(user);
        log.info("New User is created: " + user);

        Optional<User> userWithIdOne = repository.findById(1L);
        log.info("User is retrieved: " + userWithIdOne);

        List<User> users = repository.findAll();
        log.info("All Users: " + users);
    }
}
