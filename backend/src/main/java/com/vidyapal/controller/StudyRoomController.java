package com.vidyapal.controller;

import com.vidyapal.model.StudyRoom;
import com.vidyapal.service.StudyRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study-rooms")
@CrossOrigin
public class StudyRoomController {

    private final StudyRoomService studyRoomService;

    public StudyRoomController(StudyRoomService studyRoomService) {
        this.studyRoomService = studyRoomService;
    }

    @GetMapping
    public List<StudyRoom> findAll(@RequestParam(value = "userId", required = false) Long userId) {
        if (userId != null) {
            return studyRoomService.findByOwner(userId);
        }
        return studyRoomService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyRoom> findOne(@PathVariable Long id) {
        return studyRoomService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
