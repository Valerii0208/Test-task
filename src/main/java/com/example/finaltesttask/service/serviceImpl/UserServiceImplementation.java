package com.example.finaltesttask.service.serviceImpl;

import com.example.finaltesttask.entity.User;
import com.example.finaltesttask.repository.UserRepository;
import com.example.finaltesttask.service.UserService;
import com.example.finaltesttask.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
        return userOptional.get();
    }

}
