package com.notes.notes_example.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {
    public ResourceNotFoundException(String resource, Object id) {
        super(String.format("%s resource with %s id not found", resource, id), HttpStatus.NOT_FOUND);
    }
}
