package com.example.notedown.controller;

import com.example.notedown.dto.NoteDTO;
import com.example.notedown.model.Note;
import com.example.notedown.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {
    private final NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    // just returns all nots
    @GetMapping("/allNotes")
    public List<Note> allNotes() {
        return noteService.getAllNotes();
    }

    // getting a note by id
    @GetMapping("allNotes/{id}")
    public Note getNote(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    // will save or add to the list for persistence
    @PostMapping
    public String saveNote(@RequestBody NoteDTO note) {
        return noteService.saveNote(note);
    }

    //    @PutMapping("update/{id}") is this neccessary?
    @PutMapping("/{id}")
    public String updateNote(@PathVariable Long id, @RequestBody NoteDTO note) {
        return noteService.updateNote(id, note);
    }

    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable Long id) {
        return noteService.deleteNote(id);
    }

    @GetMapping("/allNotes/getNotesByTitle")
    public Note getNoteByTitle(@RequestParam String title) {
        return noteService.getNoteByTitle(title);
    }

//    public List<Note> getNoteByUserId(@RequestParam Long userId) {
//        return noteService.getNotesByUserId(userId);
//    }

    public List<Note> getSortedNotes(@RequestParam String sortType) {
        return noteService.sortNotes(sortType);
    }

    public List<Note> getPaginatedNotes(@RequestParam int limit, @RequestParam int offset) {
        return noteService.getLimitedNotes(limit, offset);
    }
}