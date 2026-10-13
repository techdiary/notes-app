package com.notes.notes_example.repository;

import com.notes.notes_example.model.Note;
import com.notes.notes_example.model.NoteBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteBookRepository extends JpaRepository<NoteBook, Long> {
    @Query("SELECT nb.id from NoteBook nb")
    Page<Long> findAllByIds(Pageable pageable);

    @Query("SELECT nb from NoteBook nb LEFT JOIN FETCH nb.notes WHERE nb.id in :ids")
    List<NoteBook> findByIds(@Param("ids") List<Long> ids);

    Page<NoteBook> findByNameContaining(String title, Pageable pageable);

}
