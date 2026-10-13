package com.notes.notes_example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for creating a new note.
 * 
 * Frontend analogy: This is like your TypeScript interface for form data
 * interface CreateNoteForm { title: string; description: string; }
 * 
 * Why a record?
 * - Immutable (can't accidentally modify request data)
 * - Auto-generates getters, equals, hashCode, toString
 * - Clean, minimal syntax
 */
public record CreateNoteRequest(
    
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    String title,
    
    @NotBlank(message = "Description is required")
    @Size(max = 5000, message = "Description must not exceed 5000 characters")
    String description,

    @Size(max = 100, message = "Category must not exceed 100 characters")
    String category
    
) {}
