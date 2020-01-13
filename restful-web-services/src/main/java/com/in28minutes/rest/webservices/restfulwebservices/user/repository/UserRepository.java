package com.in28minutes.rest.webservices.restfulwebservices.user.repository;

import com.in28minutes.rest.webservices.restfulwebservices.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
