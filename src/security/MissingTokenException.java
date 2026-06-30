package com.rikkei.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Custom exception được ném khi Authorization Token bị thiếu hoặc không hợp lệ
 * Kế thừa từ Spring Security's AuthenticationException
 *
 * Lợi ích:
 * - Tích hợp tự động với Spring Security's exception handling
 * - AuthenticationEntryPoint có thể catch và xử lý
 * - Trả về HTTP 401 Unauthorized tự động
 * - Phù hợp hơn cho authentication failure
 */
public class MissingTokenException extends AuthenticationException {

    public MissingTokenException(String message) {
        super(message);
    }

    public MissingTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}

