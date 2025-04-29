package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Example of a simple REST controller using Spring-like annotations
// Note: This requires a web framework like Spring Boot to run as a web application
@RestController // Indicates this class is a REST controller
@RequestMapping("/api/users") // Base path for this controller's endpoints
public class UserController {

    private final UserService userService; // Dependency on UserService

    // Constructor for dependency injection (Spring would inject UserService here)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to get a user by ID
    @GetMapping("/{id}") // Maps GET requests to /api/users/{id}
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        // Return 200 OK with user if found, or 404 Not Found if not found
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to get all users
    @GetMapping // Maps GET requests to /api/users
    public List<User> getAllUsers() {
        return userService.getAllUsers(); // Returns the list of users
    }

    // Endpoint to create a new user
    @PostMapping // Maps POST requests to /api/users
    @ResponseStatus(HttpStatus.CREATED) // Sets the response status to 201 Created on success
    public User createUser(@RequestBody User user) {
        // Creates a user using the service and returns the saved user (with ID)
        return userService.createUser(user.getName(), user.getEmail());
    }

    // Endpoint to delete a user (example - typically would delete by ID)
    // For simplicity, this example assumes a User object is sent in the request body
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT) // Sets the response status to 204 No Content on success
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user); // Deletes the user using the service
    }
}