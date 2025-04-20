package com.example.notedown.service;

import com.example.notedown.dto.NoteDTO;
import com.example.notedown.model.Note;
import com.example.notedown.model.User;
import com.example.notedown.repository.NoteRepository;
import com.example.notedown.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));
    }

    public String saveNote(NoteDTO note) {
        Note newNote = new Note();
//        User user = userRepository.findById(note.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
//        newNote.setUser(user);
        User user  = new User("as;dlkfj", "sdfsdfds");
        newNote.setTitle(note.getTitle());
        newNote.setContent(note.getContent());
        newNote.setCreatedAt(LocalDateTime.now());
        newNote.setUpdatedAt(LocalDateTime.now());
        noteRepository.save(newNote);
        return note.getContent() + " has been saved";
    }

    public String updateNote(Long id, NoteDTO note) {
        Note noteToUpdate = noteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));

        noteToUpdate.setTitle(note.getTitle());
        noteToUpdate.setContent(note.getContent());
        noteRepository.save(noteToUpdate);
        return note.getContent() + " has been updated";
    }

    public String deleteNote(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));

        noteRepository.deleteById(id);
        return "Note with ID " + id + " has been deleted";
    }

    public Note getNoteByTitle(String title) {
        return noteRepository.findByTitle(title)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note with title not found"));
    }

//    public List<Note> getNotesByUserId(Long userId) {
//        return noteRepository.findByUserId(userId);
//    }

    public List<Note> sortNotes(String sortType) {
        List<Note> notes = noteRepository.findAll();

        if (sortType.equalsIgnoreCase("asc")) {
            notes.sort(Comparator.comparing(Note::getUpdatedAt));
        } else if (sortType.equalsIgnoreCase("desc")) {
            notes.sort(Comparator.comparing(Note::getUpdatedAt).reversed());
        }
        return notes;
    }

    public List<Note> getLimitedNotes(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        return noteRepository.findAll(pageable).getContent();
    }
}
