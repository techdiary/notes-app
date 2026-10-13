package com.notes.notes_example.dto;


import com.notes.notes_example.model.NoteBook;

import java.time.LocalDateTime;

public record NoteResponse(

        Long id,
        String title,
        String description,
        String category,
        NoteBookSummary noteBook,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
