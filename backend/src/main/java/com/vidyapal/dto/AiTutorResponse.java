package com.vidyapal.dto;

import java.util.List;

public class AiTutorResponse {

    private String summary;
    private List<String> keyPoints;
    private List<String> followUpQuestions;

    public AiTutorResponse(String summary, List<String> keyPoints, List<String> followUpQuestions) {
        this.summary = summary;
        this.keyPoints = keyPoints;
        this.followUpQuestions = followUpQuestions;
    }

    public String getSummary() {
        return summary;
    }

    public List<String> getKeyPoints() {
        return keyPoints;
    }

    public List<String> getFollowUpQuestions() {
        return followUpQuestions;
    }
}
