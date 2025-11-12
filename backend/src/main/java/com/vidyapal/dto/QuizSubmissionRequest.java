package com.vidyapal.dto;

import jakarta.validation.constraints.NotNull;

public class QuizSubmissionRequest {

    @NotNull
    private Integer selectedOptionIndex;

    public Integer getSelectedOptionIndex() {
        return selectedOptionIndex;
    }

    public void setSelectedOptionIndex(Integer selectedOptionIndex) {
        this.selectedOptionIndex = selectedOptionIndex;
    }
}
