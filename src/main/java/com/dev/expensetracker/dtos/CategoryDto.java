package com.dev.expensetracker.dtos;

import java.util.UUID;

public record CategoryDto(
        UUID id,
        String name,
        String description

) {
}
