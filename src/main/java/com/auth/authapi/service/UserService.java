package com.auth.authapi.service;

import com.auth.authapi.entity.User;
import com.auth.authapi.repository.UserRepository;
import com.auth.authapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User addUser(User user) {
        return repository.save(user);
    }

    public User getUserById(int id) {
        return repository.findById(id).orElse(null);
    }

    public String deleteUser(int id) {
        repository.deleteById(id);
        return "User deleted successfully";
    }

    public User updateUser(int id, User user) {
        User existingUser = repository.findById(id).orElse(null);

        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            return repository.save(existingUser);
        }

        return null;
    }

    public String registerUser(User user) {
        List<User> users = repository.findAll();

        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                return "Username already exists";
            }
        }

        repository.save(user);
        return "User registered successfully";
    }

    public String loginUser(String username, String password) {

        User user = repository.findByUsername(username);

        if (user == null) {
            return "User not found";
        }

        if (!user.getPassword().equals(password)) {
            return "Invalid password";
        }

        return JwtUtil.generateToken(username);
    }
}