package com.notes.notes_example.dto.notebook;

import com.notes.notes_example.dto.NoteResponse;
import com.notes.notes_example.model.Note;

import java.time.LocalDateTime;
import java.util.List;

public record NoteBookResponse(
        Long id,
        String name,
        String description,
        List<NoteResponse> notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
