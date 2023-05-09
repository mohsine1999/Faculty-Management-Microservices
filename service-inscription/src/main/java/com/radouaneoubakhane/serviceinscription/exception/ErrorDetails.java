package com.radouaneoubakhane.serviceinscription.exception;




import lombok.*;

import java.time.LocalDateTime;



@AllArgsConstructor
@Getter
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;
}
