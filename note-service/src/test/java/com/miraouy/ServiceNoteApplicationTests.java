package com.miraouy;

import com.miraouy.Exception.Note.NoteNotFound;
import com.miraouy.dto.Response.NoteResponseDto;
import com.miraouy.model.Note;
import com.miraouy.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceNoteApplicationTests {

    @Test
    void contextLoads(NoteService noteService) throws NoteNotFound {
       // Note note = noteService.findNote(18L, 1L);

    }

}
