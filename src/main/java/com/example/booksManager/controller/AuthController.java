package com.example.booksManager.controller;

import com.example.booksManager.dto.auth.AuthRequest;
import com.example.booksManager.dto.auth.AuthResponse;
import com.example.booksManager.entity.User;
import com.example.booksManager.security.JWTUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @PostMapping
    AuthResponse auth(@RequestBody @Valid AuthRequest authRequest) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        return new AuthResponse(jwtUtil.generateToken((User)authentication.getPrincipal()));
    }
}
