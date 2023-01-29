package com.example.finaltesttask.service;

import com.example.finaltesttask.entity.User;
import com.example.finaltesttask.service.exception.UserNotFoundException;

import java.util.Optional;

public interface UserService {

    User createUser(User user);

    User getById(Long id) throws UserNotFoundException;
}
