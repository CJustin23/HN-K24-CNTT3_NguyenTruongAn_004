package com.rikkei.security;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Standard Error Response Model cho tất cả authentication errors
 * Được sử dụng bởi GlobalAuthenticationEntryPoint
 */
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private int status;
    private String error;
    private String message;
    private String timestamp;
    private String path;

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now().format(formatter);
        this.path = path;
    }

    // Getters and Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}

