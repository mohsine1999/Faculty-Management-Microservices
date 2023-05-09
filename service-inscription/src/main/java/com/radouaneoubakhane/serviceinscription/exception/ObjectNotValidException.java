package com.radouaneoubakhane.serviceinscription.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Data
public class ObjectNotValidException extends RuntimeException {

    private final Map<String, String> errorsMessages;
}
