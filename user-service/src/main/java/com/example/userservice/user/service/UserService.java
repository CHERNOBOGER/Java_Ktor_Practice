package com.example.userservice.user.service;

import com.example.userservice.user.model.User;
import com.example.userservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) { return userRepository.findById(id).get(); }

    public User updateUser(long id, User newUserData) {
        return userRepository.findById(id).map(existing -> {
            existing.setName(newUserData.getName());
            existing.setEmail(newUserData.getEmail());
            return userRepository.save(existing);
        }).orElse(null);
    }

    public boolean deleteUser(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
