package com.miraouy.Controller;


import com.miraouy.Exception.Note.NoteAlreadyExist;
import com.miraouy.Exception.Note.NoteNotFound;
import com.miraouy.dto.Request.NoteRequestDto;
import com.miraouy.dto.Response.NoteResponseDto;
import com.miraouy.model.Note;
import com.miraouy.service.NoteService;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:4200")
@RefreshScope
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping
    public NoteResponseDto addNote(@RequestBody NoteRequestDto note) throws NoteAlreadyExist {
        return noteService.addNote(note);
    }


    //listes des notes d'un etudiant de toutes les modules
    @GetMapping("/students/{apogee}")
    public List<NoteResponseDto> findNotesEtudiant(@PathVariable Long apogee) throws NoteNotFound {
        return noteService.findNotesEtudiant(apogee);
    }


    //note d'un etudiant pour un modules specifique
    @GetMapping("/students/{apogee}/modules/{idModule}")
    public NoteResponseDto findNoteStudentModule(@PathVariable Long apogee, @PathVariable Long idModule) throws NoteNotFound {
        return noteService.findNoteByStudentAndModule(apogee, idModule);
    }


    //listes des notes d'une module pour une filiere
    @GetMapping("/filieres/{idFiliere}/modules/{idModule}")
    public List<NoteResponseDto> findNoteFiliereModule(@PathVariable Long idFiliere, @PathVariable Long idModule) {
        return noteService.findNoteFiliereAndModule(idFiliere, idModule);
    }


    @DeleteMapping("/students/{apogee}/modules/{idModule}")
    public String deleteNote(@PathVariable Long apogee, @PathVariable Long idModule) throws NoteNotFound {
        return noteService.deleteNote(apogee, idModule);
    }

    @PutMapping("/students/{apogee}/modules/{idModule}")
    public NoteResponseDto updateNote(@PathVariable Long apogee, @PathVariable Long idModule, @RequestBody NoteRequestDto requestDto) throws NoteNotFound {
        return noteService.updateNote(apogee, idModule, requestDto);

    }

}
