package com.notes.notes_example.controller;

import com.notes.notes_example.dto.CreateNoteRequest;
import com.notes.notes_example.dto.NoteResponse;
import com.notes.notes_example.exception.ResourceNotFoundException;
import com.notes.notes_example.mapper.NoteMapper;
import com.notes.notes_example.model.Note;
import com.notes.notes_example.repository.NoteRepository;
import com.notes.notes_example.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class NoteController {

    private final NoteRepository noteRepository;
    NoteService noteService;

    public NoteController(NoteRepository noteRepository, NoteService noteService) {
        this.noteService = noteService;
        this.noteRepository = noteRepository;
    }

    @GetMapping("/notes")
    public ResponseEntity<Map<String, Object>> getAllNotes(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            return ResponseEntity.ok(noteService.getAllNotes(title, category, page, size));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@PathVariable long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Note", id ));

        return ResponseEntity.ok(NoteMapper.toResponse(note));
    }

    @PostMapping("/notes")
    public ResponseEntity<Note> createNote(
            @Valid @RequestBody CreateNoteRequest note
    ) {
        try {
            Note _note = noteRepository.save(NoteMapper.toEntity(note));
            return new ResponseEntity<>(_note, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNoteById(@PathVariable long id, @RequestBody Note newNote) {
        try {
            Optional<Note> note = noteRepository.findById(id);
            if(note.isPresent()){
                Note _note = note.get();
                _note.setTitle(newNote.getTitle());
                _note.setDescription(newNote.getDescription());
                _note.setCategory((newNote.getCategory()));
                return new ResponseEntity<>(noteRepository.save(_note), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<HttpStatus> deleteNoteById(@PathVariable long id) {
        try {
            noteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/notes")
    public ResponseEntity<HttpStatus> deleteAllNotes() {
        try {
            noteRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
