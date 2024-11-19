package com.example.demo.service;

import com.example.demo.repo.UserRepository;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add("ROLE_USER");
        userRepository.save(user);
    }

    public void createAdmin(String username, String password) {
        User admin = new User();
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        admin.setRoles(roles);
        userRepository.save(admin);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}