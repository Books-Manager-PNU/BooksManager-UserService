package com.example.booksManager.service.impl;

import com.example.booksManager.dto.UserRequestDto;
import com.example.booksManager.dto.UserResponseDto;
import com.example.booksManager.entity.Role;
import com.example.booksManager.entity.User;
import com.example.booksManager.exception.WebException;
import com.example.booksManager.mapper.UserMapper;
import com.example.booksManager.repository.UserRepository;
import com.example.booksManager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto create(UserRequestDto user) {
        //Let's check if user already registered with us
        if(checkIfUserExist(user.email())){
            throw new WebException(HttpStatus.CONFLICT, "User already exists");
        }
        User userEntity = userMapper.toEntity(user);
        userEntity.setRole(Role.USER);
        encodePassword(userEntity, user);
        return userMapper.toUserResponseDto(userRepository.save(userEntity));
    }

    @Override
    public UserResponseDto get(Long id) {
        User user = getExistingBookById(id);
        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userDto) {
        User user = getExistingBookById(id);
        userMapper.updateEntity(userDto, user);
        userRepository.save(user);
        return userMapper.toUserResponseDto(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow(
                () -> new WebException(HttpStatus.NOT_FOUND, "User not found")
        );
    }

    private void encodePassword(User userEntity, UserRequestDto user){
        userEntity.setPassword(passwordEncoder.encode(user.password()));
    }

    private boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private User getExistingBookById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new WebException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
