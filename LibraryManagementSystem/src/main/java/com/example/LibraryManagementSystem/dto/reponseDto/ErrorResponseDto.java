package com.example.LibraryManagementSystem.dto.reponseDto;

import java.time.Instant;

public record ErrorResponseDto(

        String message,
        String comments,
        Instant timestamp) {
}

