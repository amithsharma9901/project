package com.community.user.service;

import com.community.user.entity.User;
import com.community.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User approveUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setApproved(true);
        return userRepository.save(user);
    }
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public User updatePreferencesByEmail(String email, String preferences) {
        User user = userRepository.findByEmail(email).orElseThrow();
        user.setPreferences(preferences);
        return userRepository.save(user);
    }

}