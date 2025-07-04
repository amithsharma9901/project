package com.example.notification.config;
import org.springframework.amqp.core.Queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String NOTIFICATION_QUEUE = "notificationQueue";

    @Bean
    public Queue queue() {
        return new Queue(NOTIFICATION_QUEUE, false);
    }
}
