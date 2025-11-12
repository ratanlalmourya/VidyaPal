package com.vidyapal.dto;

import jakarta.validation.constraints.NotBlank;

public class AiTutorRequest {

    @NotBlank
    private String prompt;

    private Long studyRoomId;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Long getStudyRoomId() {
        return studyRoomId;
    }

    public void setStudyRoomId(Long studyRoomId) {
        this.studyRoomId = studyRoomId;
    }
}
