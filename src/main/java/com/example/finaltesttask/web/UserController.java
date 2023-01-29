package com.example.finaltesttask.web;

import com.example.finaltesttask.entity.User;
import com.example.finaltesttask.service.exception.UserNotFoundException;
import com.example.finaltesttask.service.serviceImpl.UserServiceImplementation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImplementation userServiceImplementation;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public User create(@RequestBody User user) {
        return userServiceImplementation.createUser(user);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/user/{id}")
    public User getById(@PathVariable("id") Long id) throws UserNotFoundException {
        return userServiceImplementation.getById(id);
    }
}
