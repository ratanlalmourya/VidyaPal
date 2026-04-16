package com.vidyapal.controller;

import com.vidyapal.dto.ErrorResponse;
import com.vidyapal.model.Notification;
import com.vidyapal.service.NotificationService;
import com.vidyapal.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;

    public NotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getForUser(@PathVariable Long userId) {
        if (userService.findById(userId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                            "User not found with id " + userId));
        }
        return ResponseEntity.ok(notificationService.findLatestForUser(userId));
    }
}
