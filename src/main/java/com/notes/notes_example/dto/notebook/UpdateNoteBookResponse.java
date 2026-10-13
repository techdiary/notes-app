package com.notes.notes_example.dto.notebook;


import java.time.LocalDateTime;

public record UpdateNoteBookResponse(
        Long id,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
