package com.example.notedown.repository;

import com.example.notedown.model.Note;
import com.example.notedown.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findByTitle(String title);

    Optional<List<Note>> findAllByUser(User user);
//    List<Note> findByUserId(Long userId);
}
