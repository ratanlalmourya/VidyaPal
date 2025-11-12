package com.vidyapal.controller;

import com.vidyapal.dto.AiTutorRequest;
import com.vidyapal.dto.AiTutorResponse;
import com.vidyapal.service.AiTutorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai-tutor")
@CrossOrigin
public class AiTutorController {

    private final AiTutorService aiTutorService;

    public AiTutorController(AiTutorService aiTutorService) {
        this.aiTutorService = aiTutorService;
    }

    @PostMapping("/prompt")
    public ResponseEntity<AiTutorResponse> prompt(@RequestBody @Valid AiTutorRequest request) {
        return ResponseEntity.ok(aiTutorService.generateResponse(request));
    }
}
