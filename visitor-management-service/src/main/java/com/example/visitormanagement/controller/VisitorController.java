package com.example.visitormanagement.controller;

import com.example.visitormanagement.model.Visitor;
import com.example.visitormanagement.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    private final VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }
//admin can add staff
    @PostMapping("/add")
    @PreAuthorize("hasRole('STAFF')")
    public Visitor addVisitor(@RequestBody Visitor visitor) {
        return visitorService.addVisitor(visitor);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<Visitor> getVisitorsByUser(@PathVariable Long userId) {
        return visitorService.getVisitorsByUser(userId);
    }

    @GetMapping("/staff/{staffId}")
    @PreAuthorize("hasRole('STAFF')")
    public List<Visitor> getVisitorsByStaff(@PathVariable Long staffId) {
        return visitorService.getVisitorsByStaff(staffId);
    }
}
