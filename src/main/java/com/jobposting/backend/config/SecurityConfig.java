package com.jobposting.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .cors(cors -> cors.disable()) // CORS 기본 비활성화 (테스트용)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // 로그인/회원가입 허용
                        .anyRequest().permitAll() // 나머지도 우선 전부 허용 (테스트 단계)
                )
                .formLogin(form -> form.disable()) // 기본 로그인 폼 제거
                .httpBasic(httpBasic -> httpBasic.disable()); // 기본 인증 비활성화

        return http.build();
    }
}
