package com.notes.notes_example;

import com.notes.notes_example.dto.notebook.CreateNoteBookRequest;
import com.notes.notes_example.metadata.NotebookAction;
import com.notes.notes_example.service.NoteBookService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NotebookActionMetadataTests {
    @Test
    void annotationShouldBeAvailableAtRuntime() throws Exception {
        Method method = NoteBookService.class.getDeclaredMethod(
                "getNoteBookResponseEntity",
                CreateNoteBookRequest.class
        );

        NotebookAction annotation =
                method.getAnnotation(NotebookAction.class);

        assertNotNull(annotation);
        assertEquals("create", annotation.value());
    }
}
