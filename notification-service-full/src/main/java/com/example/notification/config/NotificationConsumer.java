//package com.example.notification.config;
//
//import java.util.Optional;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.example.notification.entity.Notification;
//import com.example.notification.repository.NotificationRepository;
//import com.example.notification.service.TwilioService;
//
//@Component
//public class NotificationConsumer {
//
//    @Autowired
//    private TwilioService twilioService;
//
//    @Autowired
//    private NotificationRepository notificationRepo;
//
//    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE)
//    public void consume(Notification notification) throws InterruptedException {
//        Long userId = notification.getUserId();
//
//        // Simulate 30s wait to check if the user has seen it
//        //Thread.sleep(30000);
//
//        Optional<Notification> notifOpt = notificationRepo.findById(notification.getId());
//
//        if (notifOpt.isPresent() && !notifOpt.get().isSeen()) {
//            // Fallback to SMS
//            String phone = "+919901895124"; // you implement this logic
//            String msg = "[Fallback Notification] " + notification.getTitle() + " - " + notification.getMessage();
//            twilioService.sendSms(phone, msg);
//        }
//    }
//}
