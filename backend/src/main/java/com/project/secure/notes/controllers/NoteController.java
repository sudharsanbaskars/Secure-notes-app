package com.project.secure.notes.controllers;

import com.project.secure.notes.entities.Note;
import com.project.secure.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("/note")
    public Note createNote(@RequestBody String content,
                           @AuthenticationPrincipal UserDetails userDetails) {
        String loggedInUserName = userDetails.getUsername();
        System.out.println("USER DETAILS: " + loggedInUserName);
        return noteService.createNoteForUser(loggedInUserName, content);
    }

    @GetMapping()
    public List<Note> getUserNotes(@AuthenticationPrincipal UserDetails userDetails) {
        String loggedInUserName = userDetails.getUsername();
        System.out.println("USER DETAILS: " + loggedInUserName);
        return noteService.getNotesForUser(loggedInUserName);
    }

    @PutMapping("/note/{noteId}")
    public Note updateNote(@PathVariable Long noteId,
                           @RequestBody String content,
                           @AuthenticationPrincipal UserDetails userDetails) {
        String loggedInUserName = userDetails.getUsername();
        System.out.println("USER DETAILS: " + loggedInUserName);
        return noteService.updateNoteForUser(noteId, loggedInUserName, content);
    }

    @DeleteMapping("/note/{noteId}")
    public void deleteNote(@PathVariable Long noteId, @AuthenticationPrincipal UserDetails userDetails) {
        String loggedInUserName = userDetails.getUsername();
        System.out.println("USER DETAILS: " + loggedInUserName);
        noteService.deleteNoteForUser(noteId, loggedInUserName);
    }
}
