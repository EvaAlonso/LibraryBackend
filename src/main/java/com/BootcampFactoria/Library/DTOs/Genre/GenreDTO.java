package com.BootcampFactoria.Library.DTOs.Genre;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record GenreDTO(
        @NotEmpty(message = "Name is required")
        @Size(min = 1, max=30, message = "Title must contain min 2 an max 30 characters")
        String name
) {
}
