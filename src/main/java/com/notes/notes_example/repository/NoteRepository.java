package com.notes.notes_example.repository;

import com.notes.notes_example.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("select n.id from Note n")
    Page<Long> findAllByIds(Pageable pageable);

    @Query("select n from Note n left join fetch n.notebook where n.id in :ids")
    List<Note> findByIds(@Param("ids") List<Long> ids);

    Page<Note> findByTitleContaining (String title, Pageable pageable);

    Page<Note> findByCategory(String category, Pageable pageable);

    Page<Note> findByTitleAndCategory(String title, String category, Pageable pageable);

    List<Note> findAllByIdIn(List<Long> ids);

}
