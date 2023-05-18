package com.example.notes.repository;

import com.example.notes.entity.Note;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends ListCrudRepository<Note, Integer> {

    @Modifying
    @Query("UPDATE \"Notes\" SET content = :content WHERE id = :id ")
    void updateById(
            @Param("id") Integer id,
            @Param("content") String content
    );

    List<Note> findByNameContainingOrContentContaining(String name, String content);

    List<Note> findByCreatedAtBetweenOrderByCreatedAt(Long begin, Long end);

}
