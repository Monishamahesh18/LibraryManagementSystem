package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.UserDto;
import com.example.LibraryManagementSystem.enums.Role;
import com.example.LibraryManagementSystem.model.User;
import com.example.LibraryManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void addUser(UserDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.password());
        userRepository.save(new User(userDto.username(), encodedPassword, Role.USER));
    }
}
