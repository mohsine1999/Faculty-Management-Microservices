package com.miraouy.Exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MessageError {
    private int status;
    private String error;
    private String message;
    private Date timestamp;
}
