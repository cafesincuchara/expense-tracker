package com.dev.expensetracker.features.category.dto;

import java.util.UUID;

public record CategoryDto(
        UUID id,
        String name,
        String description

) {
}
