package com.vidyapal.controller;

import com.vidyapal.dto.ErrorResponse;
import com.vidyapal.model.StudyRoom;
import com.vidyapal.service.StudyRoomService;
import com.vidyapal.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/study-rooms")
@CrossOrigin
public class StudyRoomController {

    private final StudyRoomService studyRoomService;
    private final UserService userService;

    public StudyRoomController(StudyRoomService studyRoomService, UserService userService) {
        this.studyRoomService = studyRoomService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(value = "userId", required = false) Long userId) {
        if (userId != null) {
            if (userService.findById(userId).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                "User not found with id " + userId));
            }
            return ResponseEntity.ok(studyRoomService.findByOwner(userId));
        }
        return ResponseEntity.ok(studyRoomService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        return studyRoomService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                "Study room not found with id " + id)));
    }
}
