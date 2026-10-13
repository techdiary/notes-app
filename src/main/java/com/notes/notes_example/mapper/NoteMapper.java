package com.notes.notes_example.mapper;

import com.notes.notes_example.dto.CreateNoteRequest;
import com.notes.notes_example.dto.NoteBookSummary;
import com.notes.notes_example.dto.NoteResponse;
import com.notes.notes_example.model.Note;

public class NoteMapper {

    public static Note toEntity(CreateNoteRequest noteRequest) {
        Note note = new Note();
        note.setTitle(noteRequest.title());
        note.setDescription(noteRequest.description());
        note.setCategory(noteRequest.category());
        return note;
    }

    public static NoteResponse toResponse(Note note) {

        NoteBookSummary noteBookSummary = note.getNotebook() != null
                ? new NoteBookSummary(
                    note.getNotebook().getId(),
                    note.getNotebook().getName())
                : null;

        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getDescription(),
                note.getCategory(),
                noteBookSummary,
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }
}
