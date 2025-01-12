package com.BootcampFactoria.Library.DTOs.Author;

import jakarta.validation.constraints.NotEmpty;

public record AuthorDTO(
        @NotEmpty(message = "Name is required")
        String name,
        @NotEmpty(message = "biography is required")
        String biography
) {

}
