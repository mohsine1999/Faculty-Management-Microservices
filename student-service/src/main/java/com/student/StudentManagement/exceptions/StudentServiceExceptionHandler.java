package com.student.StudentManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class StudentServiceExceptionHandler {

    @ExceptionHandler(value = {StudentServiceRequestException.class})
    private ResponseEntity<Object> handleStudentServiceRequestException ( StudentServiceRequestException e){
        StudentException studentException = new StudentException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(studentException, HttpStatus.BAD_REQUEST);
    }
}
