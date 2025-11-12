package com.vidyapal.controller;

import com.vidyapal.dto.QuizSubmissionRequest;
import com.vidyapal.dto.QuizSubmissionResponse;
import com.vidyapal.model.Quiz;
import com.vidyapal.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> findOne(@PathVariable Long id) {
        return quizService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<QuizSubmissionResponse> submit(@PathVariable Long id,
                                                         @RequestBody @Valid QuizSubmissionRequest request) {
        return quizService.findById(id)
                .map(quiz -> ResponseEntity.ok(quizService.gradeQuiz(quiz, request)))
                .orElse(ResponseEntity.notFound().build());
    }
}
