package com.miraouy.service;

import com.miraouy.Exception.Note.NoteAlreadyExist;
import com.miraouy.Exception.Note.NoteNotFound;
import com.miraouy.dto.Request.NoteRequestDto;
import com.miraouy.dto.Response.NoteResponseDto;
import com.miraouy.model.Note;

import java.util.List;

public interface NoteService {
    public NoteResponseDto addNote(NoteRequestDto note) throws NoteAlreadyExist;

    NoteResponseDto findNoteByStudentAndModule(Long apogee, Long idModule) throws NoteNotFound;

    public List<NoteResponseDto> findNotesEtudiant(Long apogee) throws NoteNotFound;

    public List<NoteResponseDto> findNoteFiliereAndModule(Long idFiliere, Long idModule);

    public String deleteNote(Long apogee, Long idModule) throws NoteNotFound;

    public NoteResponseDto updateNote(Long apogee, Long idModule, NoteRequestDto note) throws NoteNotFound;

}
