package com.example.booksManager.service.impl;

import com.example.booksManager.dto.UserRequestDto;
import com.example.booksManager.dto.auth.AuthRequest;
import com.example.booksManager.dto.auth.AuthResponse;
import com.example.booksManager.entity.Role;
import com.example.booksManager.entity.User;
import com.example.booksManager.exception.WebException;
import com.example.booksManager.mapper.UserMapper;
import com.example.booksManager.repository.UserRepository;
import com.example.booksManager.security.JwtService;
import com.example.booksManager.service.AuthService;
import com.example.booksManager.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final CommonUtils commonUtils;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(UserRequestDto userDto) {
        if(commonUtils.checkIfUserExist(userDto.email())) {
            throw new WebException(HttpStatus.CONFLICT, "User already exists");
        }

        User user = userMapper.toEntity(userDto);

        user.setRole(Role.USER);
        commonUtils.encodePassword(user, userDto);
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(
                Map.of(
                        "user_id", user.getId(),
                        "role", user.getRole()
                ), user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        User user = commonUtils.getExistingUser(request.email());

        String jwtToken = jwtService.generateToken(
                Map.of(
                        "user_id", user.getId(),
                        "role", user.getRole()
                ), user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

}
