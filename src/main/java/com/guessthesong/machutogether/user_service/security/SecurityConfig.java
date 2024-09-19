package com.guessthesong.machutogether.user_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // CSRF 보호 기능 비활성화
            .csrf(AbstractHttpConfigurer::disable)
            // 세션 관리 설정
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // HTTP 요청에 대한 인증 및 권한 설정
            .authorizeHttpRequests(auth -> auth
                // 회원가입과 로그인 엔드포인트는 모든 사용자에게 허용
                .requestMatchers("/api/users/register", "/api/users/login").permitAll()
                // 그 외의 모든 요청은 인증 필요
                .anyRequest().authenticated()
            )
            // HTTP Basic 인증 활성화 (테스트 목적, 실제 환경에서는 JWT 등을 사용해야 함)
            .httpBasic(httpBasic -> {});

        return http.build();
    }
}
