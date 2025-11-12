package com.vidyapal.controller;

import com.vidyapal.model.Notification;
import com.vidyapal.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getForUser(@PathVariable Long userId) {
        return notificationService.findLatestForUser(userId);
    }
}
