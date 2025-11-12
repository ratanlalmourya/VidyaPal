package com.vidyapal.service;

import com.vidyapal.dto.QuizSubmissionRequest;
import com.vidyapal.dto.QuizSubmissionResponse;
import com.vidyapal.model.Quiz;
import com.vidyapal.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Optional<Quiz> findById(Long id) {
        return quizRepository.findById(id);
    }

    public List<Quiz> findByStudyRoom(Long studyRoomId) {
        return quizRepository.findByStudyRoomId(studyRoomId);
    }

    public QuizSubmissionResponse gradeQuiz(Quiz quiz, QuizSubmissionRequest request) {
        boolean correct = quiz.getCorrectOptionIndex() == request.getSelectedOptionIndex();
        String explanation = correct ? "Great job! You selected the correct answer."
                : "The correct answer was option " + (quiz.getCorrectOptionIndex() + 1) + ". Review the related material.";
        List<String> recommendations = List.of(
                "Revisit the key concept in the dashboard.",
                "Ask the AI tutor for another example.",
                "Schedule a focused review session."
        );
        return new QuizSubmissionResponse(correct, explanation, recommendations);
    }
}
