package com.dev.expensetracker.features.user.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        UUID id,
        String name,
        String email,
        LocalDateTime createdAt

) {
}
