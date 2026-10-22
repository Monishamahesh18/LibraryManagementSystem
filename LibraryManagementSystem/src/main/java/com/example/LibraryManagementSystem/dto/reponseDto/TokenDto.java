package com.example.LibraryManagementSystem.dto.reponseDto;

import java.util.Date;

public record TokenDto(
        String token,
        Date expiryTime,
        String issueTime
) {
}
