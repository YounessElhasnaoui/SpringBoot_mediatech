package com.estc.mediatech.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private int status;         // e.g. 404
    private String error;       // e.g. "Not Found"
    private String message;     // e.g. "Entity with ID 123 not found"
    private LocalDateTime timestamp;  // e.g. "2025-01-01T12:34:56"

    // Constructors, getters, setters

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String error, String message, LocalDateTime timestamp) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
