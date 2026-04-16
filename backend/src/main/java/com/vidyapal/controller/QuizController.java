package com.vidyapal.controller;

import com.vidyapal.dto.ErrorResponse;
import com.vidyapal.dto.QuizSubmissionRequest;
import com.vidyapal.dto.QuizSubmissionResponse;
import com.vidyapal.model.Quiz;
import com.vidyapal.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        return quizService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                "Quiz not found with id " + id)));
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<?> submit(@PathVariable Long id,
                                    @RequestBody @Valid QuizSubmissionRequest request) {
        Optional<Quiz> quizOpt = quizService.findById(id);
        if (quizOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                            "Quiz not found with id " + id));
        }
        Quiz quiz = quizOpt.get();
        int index = request.getSelectedOptionIndex();
        if (index < 0 || index >= quiz.getOptions().size()) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                            "selectedOptionIndex must be between 0 and " + (quiz.getOptions().size() - 1)));
        }
        return ResponseEntity.ok(quizService.gradeQuiz(quiz, request));
    }
}
