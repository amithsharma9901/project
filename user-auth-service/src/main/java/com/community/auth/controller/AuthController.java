package com.community.auth.controller;

import com.community.auth.config.JwtService;
import com.community.auth.dto.AuthResponse;
import com.community.auth.dto.LoginRequest;
import com.community.auth.entity.User;
import com.community.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth2")
@CrossOrigin(origins = "http://localhost:8081")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        user.setApproved(false);  // Ensure new users are unapproved
        userService.registerUser(user); // Save in AuthService

        // Sync with User Management Service
        try {
            String userManagementUrl = "http://localhost:8087/users";
            restTemplate.postForObject(userManagementUrl, user, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Registered in AuthService but failed to sync with UserManagementService.");
        }

        return ResponseEntity.ok("User registered. Awaiting admin approval.");
    }

    // ✅ Called by UserManagementService to approve user by email
    @PutMapping("/approve/{email}")
    public String approveUser(@PathVariable String email) {
        User user = userService.getByEmail(email);
        if (user == null) {
            System.out.println("AuthService: User not found for email: " + email);
            return "User not found.";
        }

        user.setApproved(true);
        userService.updateUser(user);  // ✅ Fix: Save approved status
        return "User approved in AuthService.";
    }

    // ✅ Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userService.getByEmail(request.getEmail());
        if (user == null || !user.isApproved()) {
            return ResponseEntity.status(403).body("User not approved or not found.");
        }

        // Password check should be added if you handle passwords securely
        String token = jwtService.generateToken(user.getEmail(), user.getRole());
        return ResponseEntity.ok(new AuthResponse(token, user.getRole()));
    }
}
