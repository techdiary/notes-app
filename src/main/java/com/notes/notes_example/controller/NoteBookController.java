package com.notes.notes_example.controller;

import com.notes.notes_example.dto.notebook.*;
import com.notes.notes_example.service.NoteBookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // @Controller + @RequestBody
@RequestMapping("/api/notebooks") // Tells which path this route controller will handle
public class NoteBookController  {

    NoteBookService noteBookService;

    public NoteBookController(NoteBookService noteBookService) {
        this.noteBookService = noteBookService;
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>>  getAllNoteBooks(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            return ResponseEntity.ok(noteBookService.getMapResponseEntity(name, page, size));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("")
    public ResponseEntity<NoteBookResponse>  createNoteBook(@Valid @RequestBody CreateNoteBookRequest newNoteBook) {
        try {
            return ResponseEntity.ok(noteBookService.getNoteBookResponseEntity(newNoteBook));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PatchMapping("/{id}")
    public ResponseEntity<UpdateNoteBookResponse> updateNoteBook(@PathVariable Long id, @Valid @RequestBody UpdateNoteBookRequest newNoteBook) {
        try {
            return ResponseEntity.ok(noteBookService.updateNoteBookWithId(id, newNoteBook));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteBook(@PathVariable Long id) {
        try {
            noteBookService.deleteNoteBookWithId(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/{id}/add-notes")
    public ResponseEntity<NoteBookResponse> addNotesToNoteBook(@PathVariable Long id, @Valid @RequestBody AddNoteToNoteBookRequest newNoteBookNots) {
        try {
            return ResponseEntity.ok(noteBookService.addNotes(id, newNoteBookNots));
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
