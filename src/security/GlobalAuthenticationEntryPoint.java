package com.rikkei.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * JWT Authentication EntryPoint
 *
 * Xử lý tất cả AuthenticationException được ném từ JwtAuthenticationFilter
 * Trả về HTTP 401 với JSON error response format chuẩn
 *
 * Response Format:
 * {
 *     "error": "MISSING_TOKEN",
 *     "message": "Authorization Header is missing"
 * }
 *
 * Error Types:
 * - MISSING_TOKEN: Authorization Header thiếu
 * - INVALID_TOKEN_FORMAT: Header không bắt đầu bằng "Bearer "
 * - EMPTY_TOKEN: Token trống sau "Bearer "
 * - TOKEN_EXPIRED: Token đã hết hạn
 * - INVALID_TOKEN: Token không hợp lệ hoặc malformed
 */
@Component
public class GlobalAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                        AuthenticationException authException) throws IOException, ServletException {

        // Set HTTP status code 401 Unauthorized
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Set response content type to JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Xác định error type dựa vào exception message
        JwtErrorType errorType = determineErrorType(authException.getMessage());

        // Tạo JWT error response
        JwtErrorResponse errorResponse = new JwtErrorResponse(
            errorType,
            authException.getMessage()
        );

        // Ghi JSON response
        response.getWriter().write(
            objectMapper.writeValueAsString(errorResponse)
        );
    }

    /**
     * Xác định JwtErrorType dựa vào exception message
     */
    private JwtErrorType determineErrorType(String message) {
        if (message == null) {
            return JwtErrorType.AUTHENTICATION_FAILED;
        }

        if (message.contains("Authorization Header")) {
            if (message.contains("thiếu") || message.contains("missing")) {
                return JwtErrorType.MISSING_TOKEN;
            }
            return JwtErrorType.INVALID_TOKEN_FORMAT;
        }

        if (message.contains("trống") || message.contains("empty")) {
            return JwtErrorType.EMPTY_TOKEN;
        }

        if (message.contains("expired") || message.contains("hết hạn")) {
            return JwtErrorType.TOKEN_EXPIRED;
        }

        if (message.contains("invalid") || message.contains("không hợp lệ") ||
            message.contains("malformed")) {
            return JwtErrorType.INVALID_TOKEN;
        }

        return JwtErrorType.AUTHENTICATION_FAILED;
    }
}


