package com.community.auth.service;

import com.community.auth.entity.User;
import com.community.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // Register method for new users — always unapproved initially
    public User registerUser(User user) {
        user.setApproved(false);  // Default: unapproved
        return userRepo.save(user);
    }

    // Update method for existing users
    public User updateUser(User user) {
        return userRepo.save(user);  // Use for approving, updating preferences, etc.
    }

    public User getByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow();
    }
}
