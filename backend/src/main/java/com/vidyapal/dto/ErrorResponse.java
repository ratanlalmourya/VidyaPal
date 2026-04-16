package com.vidyapal.dto;

import java.time.Instant;
import java.util.Map;

public class ErrorResponse {

    private int status;
    private String message;
    private Instant timestamp;
    private Map<String, String> errors;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = Instant.now();
    }

    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this(status, message);
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
