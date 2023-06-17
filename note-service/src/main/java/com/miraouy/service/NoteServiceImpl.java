package com.miraouy.service;

import com.miraouy.ClientFeign.FiliereClient;
import com.miraouy.ClientFeign.ModuleClient;
import com.miraouy.ClientFeign.StudentClient;
import com.miraouy.Exception.Note.NoteAlreadyExist;
import com.miraouy.Exception.Note.NoteNotFound;
import com.miraouy.dto.Request.NoteRequestDto;
import com.miraouy.dto.Response.Filiere;
import com.miraouy.dto.Response.ModuleF;
import com.miraouy.dto.Response.NoteResponseDto;
import com.miraouy.dto.Response.Student;
import com.miraouy.model.Note;
import com.miraouy.repository.NoteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final StudentClient studentClient;
    private final ModuleClient moduleClient;
    private final FiliereClient filiereClient;


    public NoteServiceImpl(NoteRepository noteRepository, StudentClient studentClient, ModuleClient moduleClient, FiliereClient filiereClient) {
        this.noteRepository = noteRepository;
        this.studentClient = studentClient;
        this.moduleClient = moduleClient;
        this.filiereClient = filiereClient;
    }

    // est regler
    @Override
    public NoteResponseDto addNote(NoteRequestDto noteRequestDto) throws NoteAlreadyExist {
        Note findNote = noteRepository.findByApogeeAndIdModule(noteRequestDto.getApogee(), noteRequestDto.getIdModule()).orElse(null);
        if (findNote != null) {
            throw new NoteAlreadyExist("la note deja existe");
        }
        ModuleF module = moduleClient.viewModule(noteRequestDto.getIdModule());
        System.out.println(module);
        System.out.println("helloooo****");
        Student student = studentClient.viewStudent(noteRequestDto.getApogee());
        Long idfiliere = student.getFilier().getId();
        System.out.println(student);
        Note note = Note.builder()
                .note(noteRequestDto.getNote())
                .apogee(noteRequestDto.getApogee())
                .idModule(noteRequestDto.getIdModule())
                .idFiliere(idfiliere)
                .build();
        System.out.println(note);
        Note noteSave = noteRepository.save(note);
        System.out.println("hello 2");
        // traitement pour chercher l'etudiant apres la construction de l'autre microservice
        NoteResponseDto noteResponseDto = NoteResponseDto
                .builder()
                .note(noteSave.getNote())
                .student(student)
                .module(module)
                .build();
        return noteResponseDto;
    }
    // est regler
    @Override
    public NoteResponseDto findNoteByStudentAndModule(Long apogee, Long idModule) throws NoteNotFound {
        Optional<Note> note = Optional.ofNullable(noteRepository.findByApogeeAndIdModule(apogee, idModule)
                .orElseThrow(() -> new NoteNotFound("Note not found")));
        Student student = studentClient.viewStudent(apogee);
        ModuleF module = moduleClient.viewModule(idModule);
        System.out.println(student.getApogee());

        NoteResponseDto noteResponseDto = NoteResponseDto
                .builder()
                .note(note.get().getNote())
                .student(student)
                .module(module)
                .build();
        return noteResponseDto;
    }
    // est regler
    @Override
    public List<NoteResponseDto> findNotesEtudiant(Long apogee) throws NoteNotFound {
        List<Note> notes = noteRepository.findAllByApogee(apogee);
        if (notes.isEmpty()) {
            throw new NoteNotFound("No notes found for apogee: " + apogee);
        }

        Student student = studentClient.viewStudent(apogee);
        return notes.stream()
                .map(note -> {
                    return new NoteResponseDto(note.getNote(), student, moduleClient.viewModule(note.getIdModule()));
                })
                .collect(Collectors.toList());
    }

    // est regler
    @Override
    public List<NoteResponseDto> findNoteFiliereAndModule(Long idFiliere, Long idModule) {
        List<Note> notes = noteRepository.findAllByIdFiliere(idFiliere);
        return notes.stream()
                .filter(note -> note.getIdModule().equals(idModule))
                .map(note -> {
                    Student student = studentClient.viewStudent(note.getApogee());
                    return new NoteResponseDto(note.getNote(), student, moduleClient.viewModule(note.getIdModule()));
                })
                .collect(Collectors.toList());
    }


    @Override
    public String deleteNote(Long apogee, Long idModule) throws NoteNotFound {
        Note noteToDelete = noteRepository.findByApogeeAndIdModule(apogee, idModule)
                .orElseThrow(() -> new NoteNotFound("Note not found"));
        noteRepository.delete(noteToDelete);
        return "Note Has delete Succesfuly";
    }

    @Override
    public NoteResponseDto updateNote(Long apogee, Long idModule, NoteRequestDto noteRequest) throws NoteNotFound {
        Student student = studentClient.viewStudent(apogee);
        Note noteToUpdate = noteRepository.findByApogeeAndIdModule(apogee, idModule)
                .orElseThrow(() -> new NoteNotFound("Note not found"));
        BeanUtils.copyProperties(noteRequest, noteToUpdate);
        noteRepository.save(noteToUpdate);
        return NoteResponseDto.builder()
                .student(student)
                .note(noteToUpdate.getNote())
                .build();
    }
}
