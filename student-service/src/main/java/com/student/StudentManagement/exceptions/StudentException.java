package com.student.StudentManagement.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class StudentException {
    private final String message;
    private final org.springframework.http.HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public StudentException(String message,
                            org.springframework.http.HttpStatus httpStatus,
                            ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
