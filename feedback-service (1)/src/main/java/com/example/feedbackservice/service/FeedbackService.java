
package com.example.feedbackservice.service;

import com.example.feedbackservice.model.Feedback;
import java.util.List;

public interface FeedbackService {
    Feedback addFeedback(Feedback feedback);
    List<Feedback> getFeedbackByUser(Long userId);
    List<Feedback> getUnresolvedFeedback();
    Feedback resolveFeedback(Long id);
}
