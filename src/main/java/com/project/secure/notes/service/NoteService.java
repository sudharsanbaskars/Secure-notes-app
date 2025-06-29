package com.project.secure.notes.service;

import com.project.secure.notes.entities.Note;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NoteService {
    Note createNoteForUser(String username, String content);
    Note updateNoteForUser(Long noteId, String username, String content);
    void deleteNoteForUser(Long noteId, String username);
    List<Note> getNotesForUser(String username);
}
