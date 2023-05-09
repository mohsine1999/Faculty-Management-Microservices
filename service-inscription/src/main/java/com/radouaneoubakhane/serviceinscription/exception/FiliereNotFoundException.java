package com.radouaneoubakhane.serviceinscription.exception;


public class FiliereNotFoundException extends RuntimeException {

    public FiliereNotFoundException(String filiereNotFound) {
        super(filiereNotFound);
    }
}

