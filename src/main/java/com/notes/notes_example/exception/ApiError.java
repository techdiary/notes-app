package com.notes.notes_example.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiError(
        int status,
        String error,
        String message,
        String path,
        LocalDateTime timeStamp,
        Map<String, String> fieldErrors // null when not a validation error
) {
    public ApiError(int status, String error, String message, String path){
        this(status, error, message, path, LocalDateTime.now(), null);
    }

    public ApiError(int status, String error, String message, String path, Map<String, String> fieldErrors){
        this(status, error, message, path, LocalDateTime.now(), fieldErrors);
    }
}
