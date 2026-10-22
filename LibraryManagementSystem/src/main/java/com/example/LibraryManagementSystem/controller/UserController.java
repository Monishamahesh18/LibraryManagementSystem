package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.UserDto;
import com.example.LibraryManagementSystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user/add")
    public void addUser(@Valid @RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }
}
