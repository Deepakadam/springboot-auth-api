package com.auth.authapi.controller;

import com.auth.authapi.entity.User;
import com.auth.authapi.service.UserService;
import com.auth.authapi.util.JwtUtil;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/users")
    public User addUser(@Valid @RequestBody User user) {
        return service.addUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        return service.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        return service.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody User user) {
        return service.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return service.loginUser(user.getUsername(), user.getPassword());
    }

    @GetMapping("/profile")
public String getProfile(@RequestHeader("Authorization") String token) {

    // remove "Bearer "
    token = token.substring(7);

    String username = JwtUtil.extractUsername(token);

    return "Welcome " + username;
}
}