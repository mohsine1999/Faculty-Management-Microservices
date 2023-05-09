package com.student.StudentManagement.exceptions;

public class StudentServiceRequestException extends RuntimeException {

    public StudentServiceRequestException(String message) {
        super(message);
    }

    public StudentServiceRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
