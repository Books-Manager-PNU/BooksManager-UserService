package com.example.booksManager.service.impl;

import com.example.booksManager.dto.UserRequestDto;
import com.example.booksManager.dto.UserResponseDto;
import com.example.booksManager.entity.User;
import com.example.booksManager.mapper.UserMapper;
import com.example.booksManager.repository.UserRepository;
import com.example.booksManager.service.UserService;
import com.example.booksManager.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CommonUtils commonUtils;

    @Override
    public UserResponseDto get(Long id) {
        User user = commonUtils.getExistingUser(id);
        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userDto) {
        User user = commonUtils.getExistingUser(id);
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
}
