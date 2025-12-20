package com.jobposting.backend.controller;

import com.jobposting.backend.dto.SignupRequest;
import com.jobposting.backend.dto.LoginRequest;
import com.jobposting.backend.entity.User;
import com.jobposting.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = userService.login(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        User newUser = userService.signup(request);
        return ResponseEntity.ok(newUser);
    }

}
