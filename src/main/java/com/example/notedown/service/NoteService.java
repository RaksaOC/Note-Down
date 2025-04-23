package com.example.notedown.service;

import com.example.notedown.dto.NoteDTO;
import com.example.notedown.model.Note;
import com.example.notedown.model.User;
import com.example.notedown.repository.NoteRepository;
import com.example.notedown.repository.UserRepository;
import com.example.notedown.security.CustomUserDetails;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes(User user) {
        return noteRepository.findAllByUser(user).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Note getNoteById(Long id, User user) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied: not your note");
        }

        return note;
    }

    public String saveNote(NoteDTO note) {
        Note newNote = new Note();
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newNote.setUser(userDetails.getUser());
        newNote.setTitle(note.getTitle());
        newNote.setContent(note.getContent());
        newNote.setCreatedAt(LocalDateTime.now());
        newNote.setUpdatedAt(LocalDateTime.now());
        noteRepository.save(newNote);
        return note.getContent() + " has been saved";
    }

    public String updateNote(Long noteId, NoteDTO note, User user) {
        Note noteToUpdate = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));

        if (!noteToUpdate.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied: not your note");
        }

        noteToUpdate.setTitle(note.getTitle());
        noteToUpdate.setContent(note.getContent());
        noteRepository.save(noteToUpdate);
        return note.getContent() + " has been updated";
    }

    public String deleteNote(Long noteId, User user) {
        Note noteToUpdate = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found"));
        if (!noteToUpdate.getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied: not your note");
        }

        noteRepository.deleteById(noteId);
        return "Note with ID " + noteId + " has been deleted";
    }

    public Note getNoteByTitle(String title) {
        return noteRepository.findByTitle(title)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Note with title not found"));
    }

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
