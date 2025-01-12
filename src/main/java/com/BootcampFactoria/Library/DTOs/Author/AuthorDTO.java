package com.BootcampFactoria.Library.DTOs.Author;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;


public record AuthorDTO(
        @NotEmpty(message = "Name is required")
        String name,
        @NotEmpty(message = "biography is required")
        String biography
) {

}
