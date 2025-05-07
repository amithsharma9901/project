
package com.example.feedbackservice.service.impl;

import com.example.feedbackservice.model.Feedback;
import com.example.feedbackservice.repository.FeedbackRepository;
import com.example.feedbackservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getFeedbackByUser(Long userId) {
        return feedbackRepository.findByUserId(userId);
    }

    @Override
    public List<Feedback> getUnresolvedFeedback() {
        return feedbackRepository.findByResolvedFalse();
    }

    @Override
    public Feedback resolveFeedback(Long id) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow();
        feedback.setResolved(true);
        return feedbackRepository.save(feedback);
    }
}
