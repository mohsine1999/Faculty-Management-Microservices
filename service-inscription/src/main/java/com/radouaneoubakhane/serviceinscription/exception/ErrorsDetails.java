package com.radouaneoubakhane.serviceinscription.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;


@Data
@AllArgsConstructor
public class ErrorsDetails {
    private LocalDateTime timestamp;
    private Map<String, String> message;
    private String details;
}
