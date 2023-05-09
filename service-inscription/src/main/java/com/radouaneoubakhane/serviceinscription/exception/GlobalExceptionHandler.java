package com.radouaneoubakhane.serviceinscription.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            InscriptionNotFoundException.class,
            FiliereNotFoundException.class
    })
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(
            ObjectNotValidException.class
    )
    public final ResponseEntity<ErrorsDetails> handleObjectNotValidException(ObjectNotValidException ex, WebRequest request) {
        ErrorsDetails inputsErrorsDetails = new ErrorsDetails(
                LocalDateTime.now(),
                ex.getErrorsMessages(),
                request.getDescription(false));
        return new ResponseEntity<>(inputsErrorsDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(
            InscriptionAlreadyAcceptedOrRefusedOrCanceledException.class
    )
    public final ResponseEntity<ErrorDetails> handleInscriptionAlreadyAcceptedOrRefusedOrCanceledException(InscriptionAlreadyAcceptedOrRefusedOrCanceledException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
