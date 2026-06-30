package com.rikkei.security;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JWT Error Response Model
 * Format đơn giản cho authentication errors
 *
 * JSON Example:
 * {
 *     "error": "MISSING_TOKEN",
 *     "message": "Authorization Header is missing"
 * }
 */
public class JwtErrorResponse {

    @JsonProperty("error")
    private String error;

    @JsonProperty("message")
    private String message;

    public JwtErrorResponse() {
    }

    public JwtErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public JwtErrorResponse(JwtErrorType errorType, String message) {
        this.error = errorType.getCode();
        this.message = message;
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

    @Override
    public String toString() {
        return "JwtErrorResponse{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

