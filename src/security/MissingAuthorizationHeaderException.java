package com.rikkei.security;

/**
 * Exception được ném khi Authorization Header bị thiếu hoặc không hợp lệ
 */
public class MissingAuthorizationHeaderException extends IllegalArgumentException {

    public MissingAuthorizationHeaderException(String message) {
        super(message);
    }

    public MissingAuthorizationHeaderException(String message, Throwable cause) {
        super(message, cause);
    }
}

