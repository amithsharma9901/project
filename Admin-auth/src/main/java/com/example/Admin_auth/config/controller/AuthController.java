package com.example.Admin_auth.config.controller;

import com.example.Admin_auth.dto.AuthResponse;
import com.example.Admin_auth.dto.LoginRequest;
import com.example.Admin_auth.entity.Admin;
import com.example.Admin_auth.repository.AdminRepository;
import com.example.Admin_auth.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth1")
@CrossOrigin(origins = "http://localhost:8081")
public class AuthController {

    @Autowired
    private AdminRepository userRepo;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        // Find user by email in the database
        Admin user = userRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("❌ User not found"));

        // Verify the plain-text password
        if (!loginRequest.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("❌ Invalid credentials");
        }

        // Ensure the user is an admin
        if (!user.getRole().equals("ROLE_ADMIN")) {
            throw new RuntimeException("❌ You do not have admin privileges.");
        }

        // Generate token for the admin
        String token = jwtService.generateToken(user.getEmail(), "ROLE_ADMIN");

        // Return the token in the response
        return new AuthResponse(token, "ROLE_ADMIN");
    }
}
