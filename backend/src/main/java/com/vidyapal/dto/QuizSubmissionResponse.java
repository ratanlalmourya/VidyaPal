package com.vidyapal.dto;

import java.util.List;

public class QuizSubmissionResponse {

    private boolean correct;
    private String explanation;
    private List<String> recommendations;

    public QuizSubmissionResponse(boolean correct, String explanation, List<String> recommendations) {
        this.correct = correct;
        this.explanation = explanation;
        this.recommendations = recommendations;
    }

    public boolean isCorrect() {
        return correct;
    }

    public String getExplanation() {
        return explanation;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }
}
