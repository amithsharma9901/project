package com.example.feedbackservice.controller;

import com.example.feedbackservice.model.Feedback;
import com.example.feedbackservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/feedback")
@CrossOrigin(origins = "http://localhost:8081")

public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    // USER can submit feedback
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add")
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        return feedbackService.addFeedback(feedback);
    }

    // USER can view their own feedback
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}")
    public List<Feedback> getFeedbackByUser(@PathVariable Long userId) {
        return feedbackService.getFeedbackByUser(userId);
    }

    // ADMIN can see unresolved feedback
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/unresolved")
    public List<Feedback> getUnresolvedFeedback() {
        return feedbackService.getUnresolvedFeedback();
    }

    // ADMIN can mark feedback as resolved
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/resolve/{id}")
    public Feedback resolveFeedback(@PathVariable Long id) {
        return feedbackService.resolveFeedback(id);
    }
}
