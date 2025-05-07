package com.community.user.controller;

import com.community.user.entity.User;
import com.community.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    // 📌 Called by AuthService to sync user data
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // ✅ Admin approves user by email
    @PutMapping("/approve/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public String approveUserByEmail(@PathVariable String email) {
        User user = userService.getByEmail(email);
        if (user == null) {
            return "User not found.";
        }

        user.setApproved(true);
        userService.saveUser(user);

        // Sync approval with AuthService
        String authServiceUrl = "http://localhost:8086/auth2/approve/" + email;
        try {
            restTemplate.put(authServiceUrl, null);
        } catch (Exception e) {
            e.printStackTrace();
            return "User approved in UserManagementService but failed to sync with AuthService.";
        }

        return "User approved successfully in both services.";
    }

    // 🔐 Get user by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // 🔐 Get user by email (used by other microservices)
    @GetMapping("/email/{email}")
  //  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }

    // 🔐 Admin can list all users
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 🔐 User updates preferences
    @PutMapping("/preferences")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public User updatePreferences(@RequestBody String preferences, @RequestHeader("email") String emailFromToken) {
        return userService.updatePreferencesByEmail(emailFromToken, preferences);
    }
}
