package com.rikkei.security;

/**
 * JWT Error Type Enumeration
 * Định nghĩa các loại lỗi authentication
 */
public enum JwtErrorType {

    MISSING_TOKEN("MISSING_TOKEN", "Authorization Header is missing"),
    INVALID_TOKEN_FORMAT("INVALID_TOKEN_FORMAT", "Invalid Bearer token format"),
    EMPTY_TOKEN("EMPTY_TOKEN", "Token cannot be empty"),
    TOKEN_EXPIRED("TOKEN_EXPIRED", "Token has expired"),
    INVALID_TOKEN("INVALID_TOKEN", "Invalid or malformed token"),
    AUTHENTICATION_FAILED("AUTHENTICATION_FAILED", "Authentication failed");

    private final String code;
    private final String defaultMessage;

    JwtErrorType(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}

