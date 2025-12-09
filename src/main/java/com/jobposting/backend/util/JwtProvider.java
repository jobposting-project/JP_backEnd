package com.jobposting.backend.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    // HS256 비밀키 생성
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 토큰 만료 시간 (1시간)
    private final long expiration = 1000L * 60 * 60;

    // JWT 생성
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey)
                .compact();
    }
}
