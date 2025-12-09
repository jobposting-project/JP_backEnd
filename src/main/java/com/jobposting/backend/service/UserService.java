package com.jobposting.backend.service;

import com.jobposting.backend.dto.SignupRequest;
import com.jobposting.backend.dto.LoginRequest;
import com.jobposting.backend.entity.User;
import com.jobposting.backend.repository.UserRepository;

import com.jobposting.backend.util.JwtProvider;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String login(LoginRequest request) {

        // 1. username으로 유저 조회
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // 3.
        return jwtProvider.generateToken(user.getUsername(), user.getRole());
    }

    public User signup(SignupRequest request) {

        // 1. username 중복 체크
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("이미 존재하는 사용자입니다.");
        }

        // 2. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 3. User 엔티티 생성
        User newUser = User.builder()
                .username(request.getUsername())
                .password(encodedPassword)
                .email(request.getEmail())
                .name(request.getName())
                .role("USER")
                .build();

        // 4. 저장
        return userRepository.save(newUser);
    }

}
