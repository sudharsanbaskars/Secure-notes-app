package com.project.secure.notes.service.impl;

import com.project.secure.notes.entities.Note;
import com.project.secure.notes.repositories.NoteRepository;
import com.project.secure.notes.service.AuditLogService;
import com.project.secure.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private AuditLogService auditLogService;
    @Override
    public Note createNoteForUser(String username, String content) {
        Note note = new Note();
        note.setContent(content);
        note.setOwnerUsername(username);
        auditLogService.logNoteCreation(username, note);
        return noteRepository.save(note);
    }

    @Override
    public Note updateNoteForUser(Long noteId, String username, String content) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
        note.setContent(content);
        note.setOwnerUsername(username);
        auditLogService.logNoteUpdate(username, note);
        return noteRepository.save(note);
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        noteRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
        noteRepository.deleteById(noteId);
        auditLogService.logNoteDeletion(username, noteId);
    }

    @Override
    public List<Note> getNotesForUser(String username) {
        return noteRepository.findByOwnerUsername(username);
    }
}
