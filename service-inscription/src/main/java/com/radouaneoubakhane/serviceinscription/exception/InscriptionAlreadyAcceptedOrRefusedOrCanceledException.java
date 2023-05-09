package com.radouaneoubakhane.serviceinscription.exception;

public class InscriptionAlreadyAcceptedOrRefusedOrCanceledException extends RuntimeException {
    public InscriptionAlreadyAcceptedOrRefusedOrCanceledException(String inscriptionAlreadyAccepted) {
        super(inscriptionAlreadyAccepted);
    }
}
