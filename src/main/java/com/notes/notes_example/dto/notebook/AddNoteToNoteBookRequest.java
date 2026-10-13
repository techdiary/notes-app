package com.notes.notes_example.dto.notebook;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AddNoteToNoteBookRequest(
        @NotEmpty(message = "The note list ids can not be empty")
        List<Long> noteIds
) {
}
