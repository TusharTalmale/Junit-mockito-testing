package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repo.UserRepository;


import java.util.List;
import java.util.Optional;

// Service class that uses the UserRepository
public class UserService {

    // Dependency on UserRepository
    private final UserRepository userRepository;

    // Constructor for dependency injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to create a new user
    public User createUser(String name, String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        User newUser = new User(name, email);
        return userRepository.save(newUser); // Delegates saving to the repository
    }

    // Method to get a user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id); // Delegates finding to the repository
    }

    // Method to get all users
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Delegates finding all to the repository
    }

    // Method to delete a user
    public void deleteUser(User user) {
        userRepository.delete(user); // Delegates deletion to the repository
    }

    public void validateUser(User user) {
        if (user.getName().isEmpty()) {
            throw new IllegalArgumentException("User name must not be empty");
        }

    }


}