package com.notes.notes_example.service;

import com.notes.notes_example.dto.NoteResponse;
import com.notes.notes_example.mapper.NoteMapper;
import com.notes.notes_example.model.Note;
import com.notes.notes_example.repository.NoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private Object pageNumber;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Map<String, Object> getAllNotes(String title, String category, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        boolean hasTitle = title != null && !title.isBlank();
        boolean hasCategory = category != null && !category.isBlank();

        List<NoteResponse> notes;
        long totalItems;
        int pageNumber;
        int totalPages;

        if (!hasTitle && !hasCategory) {
            Page<Long> ids = noteRepository.findAllByIds(paging);
            List<Note> noteWithNoteBooks = noteRepository.findByIds(ids.getContent());
            notes = noteWithNoteBooks
                    .stream()
                    .map(NoteMapper::toResponse)
                    .toList();
            totalItems = ids.getTotalElements();
            pageNumber = ids.getNumber();
            totalPages = ids.getTotalPages();
        } else if(hasTitle && hasCategory){
            Page<Note> pageNotes = noteRepository.findByTitleAndCategory(title, category, paging);
            notes = pageNotes.getContent()
                    .stream()
                    .map(NoteMapper::toResponse)
                    .toList();
            totalItems = pageNotes.getTotalElements();
            pageNumber = pageNotes.getNumber();
            totalPages = pageNotes.getSize();
        } else if(hasTitle) {
            Page<Note> pageNotes = noteRepository.findByTitleContaining(title, paging);
            notes = pageNotes.getContent()
                    .stream()
                    .map(NoteMapper::toResponse)
                    .toList();
            totalItems = pageNotes.getTotalElements();
            pageNumber = pageNotes.getNumber();
            totalPages = pageNotes.getSize();
        } else {
            Page<Note> pageNotes = noteRepository.findByCategory(category, paging);
            notes = pageNotes.getContent()
                    .stream()
                    .map(NoteMapper::toResponse)
                    .toList();
            totalItems = pageNotes.getTotalElements();
            pageNumber = pageNotes.getNumber();
            totalPages = pageNotes.getSize();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("notes", notes);
        response.put("currentPage", pageNumber);
        response.put("totalItems", totalItems);
        response.put("totalPages", totalPages);

        return response;
    }
}
