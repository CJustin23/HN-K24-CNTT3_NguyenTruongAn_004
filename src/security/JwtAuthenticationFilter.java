package com.rikkei.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * JWT Authentication Filter
 *
 * Trách nhiệm:
 * - Trích xuất JWT token từ Authorization Header
 * - Validate token
 * - Set Authentication vào SecurityContext
 *
 * Điểm quan trọng:
 * - Chỉ THROW exception, KHÔNG xử lý response
 * - Exception sẽ được catch bởi ExceptionTranslationFilter
 * - GlobalAuthenticationEntryPoint sẽ xử lý response
 *
 * Flow khi error:
 * 1. Throw MissingTokenException (AuthenticationException)
 * 2. ExceptionTranslationFilter catch
 * 3. GlobalAuthenticationEntryPoint.commence() xử lý
 * 4. HTTP 401 + JSON response
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String SECRET_KEY = "rikkei_secret_key_super_secure_do_not_share";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final int BEARER_LENGTH = BEARER_PREFIX.length();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Trích xuất Authorization Header
        String authHeader = request.getHeader("Authorization");

        // Kiểm tra null
        if (authHeader == null || authHeader.isEmpty()) {
            throw new MissingTokenException("Authorization Header is missing");
        }

        // Kiểm tra format "Bearer "
        if (!authHeader.startsWith(BEARER_PREFIX)) {
            throw new MissingTokenException("Invalid Bearer token format");
        }

        // Trích xuất token
        String token = authHeader.substring(BEARER_LENGTH);

        // Kiểm tra token có trống không
        if (token.isEmpty()) {
            throw new MissingTokenException("Token cannot be empty");
        }

        // Parse và validate JWT token
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();

            // Set Authentication vào SecurityContext nếu username hợp lệ
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (JwtException e) {
            // JWT parsing error (invalid, expired, malformed, etc.)
            throw new MissingTokenException("Invalid or malformed token", e);
        } catch (Exception e) {
            // Unexpected error
            throw new MissingTokenException("Authentication failed: " + e.getMessage(), e);
        }

        // Cho request tiếp tục đi qua filter chain
        filterChain.doFilter(request, response);
    }
}



