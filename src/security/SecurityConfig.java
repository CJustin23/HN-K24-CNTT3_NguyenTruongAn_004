package com.rikkei.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final GlobalAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Vô hiệu hóa CSRF vì chúng ta sử dụng JWT (stateless)
            .csrf().disable()

            // 2. Cấu hình xử lý ngoại lệ tập trung
            .exceptionHandling()
                // Đây là nơi chúng ta thay thế xử lý mặc định bằng EntryPoint tùy chỉnh
                .authenticationEntryPoint(authenticationEntryPoint)
            .and()

            // 3. Quản lý Session: Stateless (không lưu session trên server)
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

            // 4. Cấu hình phân quyền các endpoint
            .authorizeRequests()
                .antMatchers("/auth/**", "/public/**").permitAll() // Cho phép truy cập không cần token
                .anyRequest().authenticated() // Tất cả các request khác đều cần xác thực
            .and();

        // 5. Thêm JwtAuthenticationFilter vào trước filter xác thực mặc định của Spring
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}