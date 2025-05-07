package com.example.notification.service;

import com.example.notification.config.RabbitMQConfig;
import com.example.notification.controller.NotificationWebSocketController;
import com.example.notification.dto.NotificationRequest;
import com.example.notification.entity.Notification;
import com.example.notification.repository.NotificationRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private NotificationWebSocketController webSocketController;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = Logger.getLogger(NotificationService.class.getName());

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public Notification sendNotification(NotificationRequest request) {
        try {
            // 🚨 Fetch userId from User Management Service using email
            String userServiceUrl = "http://localhost:8087/users/email/" + request.getEmail(); // adjust port if needed
            LOGGER.info("Calling User Management Service with URL: " + userServiceUrl);

            // Fetch user details
            Map<String, Object> user = restTemplate.getForObject(userServiceUrl, Map.class);

            if (user == null || !user.containsKey("id")) {
                LOGGER.severe("User not found with email: " + request.getEmail());
                throw new IllegalArgumentException("User with email " + request.getEmail() + " not found.");
            }

            Long userId = ((Number) user.get("id")).longValue();
            LOGGER.info("Found userId: " + userId);

            // Build the Notification entity
            Notification notification = Notification.builder()
                    .userId(userId)
                    .title(request.getTitle())
                    .message(request.getMessage())
                    .type(request.getType())
                    .timestamp(LocalDateTime.now())
                    .seen(false)
                    .build();

            // Save the notification to the database
            Notification saved = repository.save(notification);
            LOGGER.info("Notification saved with ID: " + saved.getId());

            // Optional messaging to WebSocket and RabbitMQ (uncomment if needed)
            // webSocketController.sendNotificationToUser(notification.getUserId(), saved);
            // rabbitTemplate.convertAndSend(RabbitMQConfig.NOTIFICATION_QUEUE, saved);

            return saved;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while sending notification", e);
            throw new RuntimeException("Failed to send notification", e);
        }
    }

    public List<Notification> getUnread(Long userId) {
        return repository.findByUserIdAndSeenFalse(userId);
    }

    public List<Notification> getHistory(Long userId) {
        return repository.findByUserId(userId);
    }

    public boolean markSeen(Long id) {
        Optional<Notification> notificationOpt = repository.findById(id);
        if (notificationOpt.isPresent()) {
            Notification notification = notificationOpt.get();
            notification.setSeen(true);
            repository.save(notification);
            return true;
        }
        return false;
    }

    // ✅ NEW: Delete notification by ID
    public boolean deleteNotification(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // ✅ NEW: Get all notifications
    public List<Notification> getAllNotifications() {
        return repository.findAllByOrderByTimestampDesc();
    }
}
