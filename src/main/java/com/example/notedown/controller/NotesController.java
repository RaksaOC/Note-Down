package com.example.notedown.controller;

import com.example.notedown.dto.NoteDTO;
import com.example.notedown.model.Note;
import com.example.notedown.security.CustomUserDetails;
import com.example.notedown.service.NoteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {
    private final NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }

    @GetMapping("/session")
    public boolean checkSession(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userDetails != null;
    }

    // just returns all nots
    @GetMapping("/allNotes")
    public List<Note> allNotes(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return noteService.getAllNotes(userDetails.getUser());
    }

    // getting a note by id
    @GetMapping("allNotes/{noteId}")
    public Note getNote(@PathVariable Long noteId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return noteService.getNoteById(noteId, userDetails.getUser());
    }

    // will save or add to the list for persistence
    @PostMapping
    public String saveNote(@RequestBody NoteDTO note) {
        return noteService.saveNote(note);
    }

    @PutMapping("/{noteId}")
    public String updateNote(@PathVariable Long noteId, @RequestBody NoteDTO note, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return noteService.updateNote(noteId, note, userDetails.getUser());
    }

    @DeleteMapping("/{noteId}")
    public String deleteNote(@PathVariable Long noteId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return noteService.deleteNote(noteId, userDetails.getUser());
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