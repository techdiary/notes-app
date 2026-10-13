package com.notes.notes_example.service;

import com.notes.notes_example.dto.notebook.*;
import com.notes.notes_example.exception.ResourceNotFoundException;
import com.notes.notes_example.mapper.NoteBookMapper;
import com.notes.notes_example.model.Note;
import com.notes.notes_example.model.NoteBook;
import com.notes.notes_example.repository.NoteBookRepository;
import com.notes.notes_example.repository.NoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class NoteBookService {

    private final NoteBookRepository noteBookRepository;
    private final NoteRepository noteRepository;

    public NoteBookService(NoteBookRepository noteBookRepository, NoteRepository noteRepository) {
        this.noteBookRepository = noteBookRepository;
        this.noteRepository = noteRepository;
    }

    public Map<String, Object> getMapResponseEntity(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        boolean hasName = name != null && !name.isEmpty();

        List<NoteBookResponse> noteBooks;
        long totalItems;
        int pageNumber;


        if(hasName){
            Page<NoteBook> pageNoteBooks = noteBookRepository.findByNameContaining(name, pageable);
            noteBooks = pageNoteBooks.getContent()
                    .stream()
                    .map(NoteBookMapper::toResponse)
                    .toList();
            totalItems = pageNoteBooks.getTotalElements();
            pageNumber = pageNoteBooks.getNumber();
        } else {
            Page<Long> ids = noteBookRepository.findAllByIds(pageable);
            List<NoteBook> notebooksWithNotes = noteBookRepository.findByIds(ids.getContent());
            noteBooks = notebooksWithNotes
                    .stream()
                    .map(NoteBookMapper::toResponse)
                    .toList();
            totalItems = ids.getTotalElements();
            pageNumber = ids.getNumber();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("notebooks", noteBooks);
        response.put("totalItems", totalItems);
        response.put("page", pageNumber);

        return response;
    }

    public NoteBookResponse getNoteBookResponseEntity(CreateNoteBookRequest newNoteBook) {
        NoteBook noteBook = noteBookRepository.save(NoteBookMapper.toEntity(newNoteBook));
        return NoteBookMapper.toResponse(noteBook);
    }


    public void deleteNoteBookWithId(Long id) {
        Optional<NoteBook> noteBook = noteBookRepository.findById(id);
        if (noteBook.isPresent()) {
            noteBookRepository.deleteById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public UpdateNoteBookResponse updateNoteBookWithId(Long id, UpdateNoteBookRequest newNoteBook) {
        NoteBook noteBookEntity = noteBookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Notebook", id));
        if (newNoteBook.name() != null) {
            noteBookEntity.setName(newNoteBook.name());
        }
        if (newNoteBook.description() != null) {
            noteBookEntity.setDescription(newNoteBook.description());
        }
        return NoteBookMapper.toUpdateNoteBookResponse(noteBookEntity);
    }

    @Transactional
    public NoteBookResponse addNotes(Long id, AddNoteToNoteBookRequest newNoteBookNots) {
        NoteBook noteBook = noteBookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            List<Note> notes = noteRepository.findAllByIdIn(newNoteBookNots.noteIds());

            for (Note note : notes) {
                noteBook.setNotes(note);
            }

            noteBookRepository.save(noteBook);
            return NoteBookMapper.toResponse(noteBook);
    }

}
