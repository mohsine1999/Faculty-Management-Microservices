package com.miraouy.Exception.Note;

import com.miraouy.Exception.MessageError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class HandlerNoteExeptions {
    @ExceptionHandler
    public ResponseEntity<Object> handleNoteNotFound(NoteNotFound ex, WebRequest request) {
        MessageError errorB = new MessageError();
        errorB.setTimestamp(new Date());
        errorB.setStatus(HttpStatus.BAD_REQUEST.value());
        errorB.setError(HttpStatus.BAD_REQUEST.name());
        errorB.setMessage(ex.getMessage());
        System.out.println("***********" + errorB.getMessage());
        return new ResponseEntity<>(errorB, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleNoteAlreadyExist(NoteAlreadyExist ex, WebRequest request) {
        MessageError errorB = new MessageError();
        errorB.setTimestamp(new Date());
        errorB.setStatus(HttpStatus.BAD_REQUEST.value());
        errorB.setError(HttpStatus.BAD_REQUEST.name());
        errorB.setMessage(ex.getMessage());
        System.out.println(errorB.getMessage());
        return new ResponseEntity<>(errorB, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
