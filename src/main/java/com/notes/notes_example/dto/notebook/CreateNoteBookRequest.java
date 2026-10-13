package com.notes.notes_example.dto.notebook;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateNoteBookRequest(

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name length must not exceed 100 characters")
        String name,

        String description
) {
}
