package com.notes.notes_example.mapper;

import com.notes.notes_example.dto.NoteBookSummary;
import com.notes.notes_example.dto.NoteResponse;
import com.notes.notes_example.dto.notebook.CreateNoteBookRequest;
import com.notes.notes_example.dto.notebook.NoteBookResponse;
import com.notes.notes_example.dto.notebook.UpdateNoteBookResponse;
import com.notes.notes_example.model.NoteBook;

import java.util.List;

public class NoteBookMapper {

    public static NoteBook toEntity(CreateNoteBookRequest noteBookRequest) {
        NoteBook noteBook = new NoteBook();
        noteBook.setName(noteBookRequest.name());
        noteBook.setDescription(noteBookRequest.description());
        return noteBook;
    }

    public static NoteBookResponse toResponse(NoteBook noteBook) {

        List<NoteResponse> notes = noteBook.getNotes().stream()
                .map(note -> new NoteResponse(
                        note.getId(),
                        note.getTitle(),
                        note.getDescription(),
                        note.getCategory(),
                        new NoteBookSummary(noteBook.getId(), noteBook.getName()),
                        note.getCreatedAt(),
                        note.getUpdatedAt()
                )).toList();

        return new NoteBookResponse(
                noteBook.getId(),
                noteBook.getName(),
                noteBook.getDescription(),
                notes,
                noteBook.getCreatedAt(),
                noteBook.getUpdatedAt()
        );
    }

    public static UpdateNoteBookResponse toUpdateNoteBookResponse(NoteBook noteBook) {
        return new UpdateNoteBookResponse(
                noteBook.getId(),
                noteBook.getName(),
                noteBook.getDescription(),
                noteBook.getCreatedAt(),
                noteBook.getUpdatedAt()
        );
    }
}
