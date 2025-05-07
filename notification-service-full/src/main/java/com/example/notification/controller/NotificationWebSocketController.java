package com.example.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.notification.entity.Notification;

@Controller
public class NotificationWebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendNotificationToUser(Long userId, Notification notification) {
        messagingTemplate.convertAndSend("/topic/notifications/" + userId, notification);
    }
}
