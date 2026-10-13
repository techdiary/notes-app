package com.notes.notes_example.dto.notebook;

public record UpdateNoteBookRequest(
        String name,
        String description
) {
}
