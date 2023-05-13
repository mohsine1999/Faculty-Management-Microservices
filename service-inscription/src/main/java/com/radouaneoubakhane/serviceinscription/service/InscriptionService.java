package com.radouaneoubakhane.serviceinscription.service;


import com.radouaneoubakhane.serviceinscription.dto.*;
import com.radouaneoubakhane.serviceinscription.exception.InscriptionAlreadyAcceptedOrRefusedOrCanceledException;
import com.radouaneoubakhane.serviceinscription.exception.InscriptionNotFoundException;
import com.radouaneoubakhane.serviceinscription.model.Inscription;
import com.radouaneoubakhane.serviceinscription.model.enums.Diplomat;
import com.radouaneoubakhane.serviceinscription.openfeingClients.EtudientClient;
import com.radouaneoubakhane.serviceinscription.repository.InscriptionRepository;
import com.radouaneoubakhane.serviceinscription.validations.ObjectsValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;
    private final EtudientClient etudientClient;
    private final ObjectsValidator<InscriptionRequest> objectsValidator;
    private final ObjectsValidator<InscriptionRequestLST> inscriptionRequestLSTObjectsValidator;
    private final ObjectsValidator<InscriptionRequestDEUST> inscriptionObjectsValidatorDEUST;


    public List<InscriptionResponse> getAllInscriptionsMST() {
        log.info("Getting all inscriptions");
        List<Inscription> inscriptions = inscriptionRepository
                .findAllByDiplomat(Diplomat.MST).stream()
                .filter(inscription -> !inscription.isCanceled() && !inscription.isAccepted() && !inscription.isRefused())
                .toList();
        return inscriptions.stream()
                .map(this::mapInscriptionToInscriptionResponse)
                .toList();
    }

    public InscriptionResponse getInscriptionById(Long inscriptionId) {
        log.info("Getting inscription with id {}", inscriptionId);
        Inscription inscription = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new InscriptionNotFoundException("Inscription not found"));
        return mapInscriptionToInscriptionResponse(inscription);
    }

    public InscriptionResponse createInscription(InscriptionRequest inscriptionRequest) {
        objectsValidator.validate(inscriptionRequest);
        
        Inscription inscription = mapInscriptionRequestToInscription(inscriptionRequest);

        Inscription savedInscription = inscriptionRepository.save(inscription);

        log.info("Creating inscription {}", inscriptionRequest);

        return mapInscriptionToInscriptionResponse(savedInscription);
    }

    public InscriptionResponse updateInscription(Long inscriptionId, InscriptionRequest inscriptionRequest) {

        objectsValidator.validate(inscriptionRequest);
        log.info("Updating inscription with id {}", inscriptionId);
        Inscription inscription = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new InscriptionNotFoundException("Inscription not found"));


        inscription.setCin(inscriptionRequest.getCin());
        inscription.setCne(inscriptionRequest.getCne());
        inscription.setNom(inscriptionRequest.getNom());
        inscription.setPrenom(inscriptionRequest.getPrenom());
        inscription.setEmail(inscriptionRequest.getEmail());
        inscription.setDateNaissance(inscriptionRequest.getDateNaissance());
        inscription.setLieuNaissance(inscriptionRequest.getLieuNaissance());
        inscription.setAdresse(inscriptionRequest.getAdresse());
        inscription.setTelephone(inscriptionRequest.getTelephone());
        inscription.setFiliereBac(inscriptionRequest.getFiliereBac());
        inscription.setMoyenneBac(inscriptionRequest.getMoyenneBac());
        inscription.setMentionBac(inscriptionRequest.getMentionBac());
        inscription.setAnneeBac(inscriptionRequest.getAnneeBac());
        inscription.setEtablissementBac(inscriptionRequest.getEtablissementBac());
        inscription.setVilleBac(inscriptionRequest.getVilleBac());
        inscription.setPaysBac(inscriptionRequest.getPaysBac());
        inscription.setDiplomeDeust(inscriptionRequest.getDiplomeDeust());
        inscription.setMoyenneDiplome(inscriptionRequest.getMoyenneDiplome());
        inscription.setMentionDiplome(inscriptionRequest.getMentionDiplome());
        inscription.setAnneeDiplome(inscriptionRequest.getAnneeDiplome());
        inscription.setEtablissementDiplome(inscriptionRequest.getEtablissementDiplome());
        inscription.setVilleDiplome(inscriptionRequest.getVilleDiplome());
        inscription.setPaysDiplome(inscriptionRequest.getPaysDiplome());
        inscription.setLicence(inscriptionRequest.getLicence());
        inscription.setMoyenneLicence(inscriptionRequest.getMoyenneLicence());
        inscription.setDiplomat(inscriptionRequest.getDiplomat());  
        inscription.setFiliereId(inscriptionRequest.getFiliereId());

        Inscription savedInscription = inscriptionRepository.save(inscription);

        return mapInscriptionToInscriptionResponse(savedInscription);
    }

    public void deleteInscription(Long inscriptionId) {
        log.info("Deleting inscription with id {}", inscriptionId);
        Inscription inscription = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new InscriptionNotFoundException("Inscription not found"));
        inscriptionRepository.delete(inscription);
    }

    public void acceptInscription(Long inscriptionId) {
        log.info("Accepting inscription with id {}", inscriptionId);
        Inscription inscription = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new InscriptionNotFoundException("Inscription not found"));

        if (!inscription.isCanceled() && !inscription.isRefused() && !inscription.isAccepted()) {
            etudientClient.SaveStudent(mapInscriptionToEtudientRequest(inscription));
            inscription.setAccepted(true);
            inscription.setRefused(false);
            inscriptionRepository.save(inscription);
        }
        else {
            throw new InscriptionAlreadyAcceptedOrRefusedOrCanceledException("Inscription already accepted or refused or canceled");
        }
    }

    private EtudientRequest mapInscriptionToEtudientRequest(Inscription inscription) {

        return EtudientRequest.builder()
                .cin(inscription.getCin())
                .apogee(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)
                .nom(inscription.getNom())
                .prenom(inscription.getPrenom())
                .cne(inscription.getCne())
                .email(inscription.getEmail())
                .phone(inscription.getTelephone())
                .dateNaissance(inscription.getDateNaissance())
                .lieuNaissance(inscription.getLieuNaissance())
                .adresse(inscription.getAdresse())
                .genre(inscription.getGenre())
                .idFiliere(inscription.getFiliereId())
                .build();
    }

    public InscriptionResponse rejectInscription(Long inscriptionId) {
        log.info("Rejecting inscription with id {}", inscriptionId);
        Inscription inscription = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new InscriptionNotFoundException("Inscription not found"));

        inscription.setRefused(true);
        inscription.setAccepted(false);

        Inscription savedInscription = inscriptionRepository.save(inscription);
        return mapInscriptionToInscriptionResponse(savedInscription);
    }

    public InscriptionResponse cancelInscription(Long inscriptionId) {
        log.info("Canceling inscription with id {}", inscriptionId);
        Inscription inscription = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new InscriptionNotFoundException("Inscription not found"));

        inscription.setCanceled(true);

        Inscription savedInscription = inscriptionRepository.save(inscription);
        return mapInscriptionToInscriptionResponse(savedInscription);
    }

    public List<InscriptionResponse> getAllAcceptedInscriptions() {
        List<Inscription> inscriptions = inscriptionRepository.findAllByIsAccepted(true);
        return inscriptions.stream()
                .map(this::mapInscriptionToInscriptionResponse)
                .toList();
    }

    public List<InscriptionResponse> getAllRejectedInscriptions() {
        List<Inscription> inscriptions = inscriptionRepository.findAllByIsRefused(true);
        return inscriptions.stream()
                .map(this::mapInscriptionToInscriptionResponse)
                .toList();
    }

    public List<InscriptionResponse> getAllCanceledInscriptions() {
        List<Inscription> inscriptions = inscriptionRepository.findAllByIsCanceled(true);
        return inscriptions.stream()
                .map(this::mapInscriptionToInscriptionResponse)
                .toList();
    }


    public void acceptInscriptionDEUST(Integer number) {
        List<Inscription> inscriptions = inscriptionRepository
                .findAllByDiplomat(Diplomat.DEUST).stream()
                .filter(inscription -> !inscription.isCanceled())
                .toList();


        List<EtudientRequest> etudientRequests = inscriptions.stream()
                .sorted(Comparator.comparing(Inscription::getMoyenneDiplome).reversed())
                .limit(number)
                .filter(inscription -> !inscription.isCanceled() && !inscription.isRefused() && !inscription.isAccepted())
                .map(inscription -> {
                    EtudientRequest etudientRequest = mapInscriptionToEtudientRequest(inscription);
                    inscription.setAccepted(true);
                    inscription.setRefused(false);
                    inscriptionRepository.save(inscription);
                    return etudientRequest;
                })
                   .toList();

        etudientClient.saveStudents(etudientRequests);
    }

    public void acceptInscriptionLST(Integer number) {
        List<Inscription> inscriptions = inscriptionRepository
                .findAllByDiplomat(Diplomat.LST).stream()
                .filter(inscription -> !inscription.isCanceled())
                .toList();

        List<EtudientRequest> etudientRequests = inscriptions.stream()
                .sorted(Comparator.comparing(Inscription::getMoyenneDiplome).reversed())
                .limit(number)
                .filter(inscription -> !inscription.isCanceled() && !inscription.isRefused() && !inscription.isAccepted())
                .map(inscription -> {
                    EtudientRequest etudientRequest = mapInscriptionToEtudientRequest(inscription);
                    inscription.setAccepted(true);
                    inscription.setRefused(false);
                    inscriptionRepository.save(inscription);
                    return etudientRequest;
                })
                .toList();

        etudientClient.saveStudents(etudientRequests);
    }

    public void acceptInscriptionMST(Integer number) {
        List<Inscription> inscriptions = inscriptionRepository
                .findAllByDiplomat(Diplomat.MST).stream()
                .filter(inscription -> !inscription.isCanceled())
                .toList();

        List<EtudientRequest> etudientRequests = inscriptions.stream()
                .sorted(Comparator.comparing(Inscription::getMoyenneDiplome).reversed())
                .limit(number)
                .filter(inscription -> !inscription.isCanceled() && !inscription.isRefused() && !inscription.isAccepted())
                .map(inscription -> {
                    EtudientRequest etudientRequest = mapInscriptionToEtudientRequest(inscription);
                    inscription.setAccepted(true);
                    inscription.setRefused(false);
                    inscriptionRepository.save(inscription);
                    return etudientRequest;
                })
                .toList();

        etudientClient.saveStudents(etudientRequests);
    }




    private Inscription mapInscriptionRequestToInscription(InscriptionRequest inscriptionRequest) {
        return Inscription.builder()
                .cin(inscriptionRequest.getCin())
                .cne(inscriptionRequest.getCne())
                .nom(inscriptionRequest.getNom())
                .prenom(inscriptionRequest.getPrenom())
                .email(inscriptionRequest.getEmail())
                .genre(inscriptionRequest.getGenre())
                .dateNaissance(inscriptionRequest.getDateNaissance())
                .lieuNaissance(inscriptionRequest.getLieuNaissance())
                .adresse(inscriptionRequest.getAdresse())
                .telephone(inscriptionRequest.getTelephone())
                .filiereBac(inscriptionRequest.getFiliereBac())
                .moyenneBac(inscriptionRequest.getMoyenneBac())
                .mentionBac(inscriptionRequest.getMentionBac())
                .anneeBac(inscriptionRequest.getAnneeBac())
                .etablissementBac(inscriptionRequest.getEtablissementBac())
                .villeBac(inscriptionRequest.getVilleBac())
                .paysBac(inscriptionRequest.getPaysBac())
                .diplomeDeust(inscriptionRequest.getDiplomeDeust())
                .moyenneDiplome(inscriptionRequest.getMoyenneDiplome())
                .mentionDiplome(inscriptionRequest.getMentionDiplome())
                .anneeDiplome(inscriptionRequest.getAnneeDiplome())
                .etablissementDiplome(inscriptionRequest.getEtablissementDiplome())
                .villeDiplome(inscriptionRequest.getVilleDiplome())
                .paysDiplome(inscriptionRequest.getPaysDiplome())
                .licence(inscriptionRequest.getLicence())
                .moyenneLicence(inscriptionRequest.getMoyenneLicence())
                .filiereId(inscriptionRequest.getFiliereId())
                .mentionLicence(inscriptionRequest.getMentionLicence())
                .anneeLicence(inscriptionRequest.getAnneeLicence())
                .etablissementLicence(inscriptionRequest.getEtablissementLicence())
                .villeLicence(inscriptionRequest.getVilleLicence())
                .villeLicence(inscriptionRequest.getPaysLicence())
                .paysLicence(inscriptionRequest.getPaysLicence())
                .diplomat(inscriptionRequest.getDiplomat())
                .build();
    }


    private InscriptionResponse mapInscriptionToInscriptionResponse(Inscription inscription) {
return InscriptionResponse.builder()
                .id(inscription.getId())
                .cin(inscription.getCin())
                .cne(inscription.getCne())
                .nom(inscription.getNom())
                .prenom(inscription.getPrenom())
                .genre(inscription.getGenre())
                .email(inscription.getEmail())
                .dateNaissance(inscription.getDateNaissance())
                .lieuNaissance(inscription.getLieuNaissance())
                .adresse(inscription.getAdresse())
                .telephone(inscription.getTelephone())
                .filiereBac(inscription.getFiliereBac())
                .moyenneBac(inscription.getMoyenneBac())
                .mentionBac(inscription.getMentionBac())
                .anneeBac(inscription.getAnneeBac())
                .etablissementBac(inscription.getEtablissementBac())
                .villeBac(inscription.getVilleBac())
                .paysBac(inscription.getPaysBac())
                .diplomeDeust(inscription.getDiplomeDeust())
                .moyenneDiplome(inscription.getMoyenneDiplome())
                .mentionDiplome(inscription.getMentionDiplome())
                .anneeDiplome(inscription.getAnneeDiplome())
                .etablissementDiplome(inscription.getEtablissementDiplome())
                .villeDiplome(inscription.getVilleDiplome())
                .paysDiplome(inscription.getPaysDiplome())
                .licence(inscription.getLicence())
                .moyenneLicence(inscription.getMoyenneLicence())
                .filiereId(inscription.getFiliereId())
                .mentionLicence(inscription.getMentionLicence())
                .anneeLicence(inscription.getAnneeLicence())
                .etablissementLicence(inscription.getEtablissementLicence())
                .villeLicence(inscription.getVilleLicence())
                .villeLicence(inscription.getPaysLicence())
                .paysLicence(inscription.getPaysLicence())
                .diplomat(inscription.getDiplomat())
                .build();
    }

    public List<InscriptionResponseDEUST> getAllInscriptionsDEUST() {
        return inscriptionRepository.findAllByDiplomat(Diplomat.DEUST).stream()
                .filter(inscription -> !inscription.isCanceled() && !inscription.isRefused() && !inscription.isAccepted())
                .map(this::mapInscriptionToInscriptionResponseDEUST)
                .toList();
    }

    private InscriptionResponseDEUST mapInscriptionToInscriptionResponseDEUST(Inscription inscription) {
        return InscriptionResponseDEUST.builder()
                .id(inscription.getId())
                .cin(inscription.getCin())
                .cne(inscription.getCne())
                .nom(inscription.getNom())
                .prenom(inscription.getPrenom())
                .genre(inscription.getGenre())
                .email(inscription.getEmail())
                .dateNaissance(inscription.getDateNaissance())
                .lieuNaissance(inscription.getLieuNaissance())
                .adresse(inscription.getAdresse())
                .telephone(inscription.getTelephone())
                .filiereBac(inscription.getFiliereBac())
                .moyenneBac(inscription.getMoyenneBac())
                .mentionBac(inscription.getMentionBac())
                .anneeBac(inscription.getAnneeBac())
                .etablissementBac(inscription.getEtablissementBac())
                .villeBac(inscription.getVilleBac())
                .paysBac(inscription.getPaysBac())
                .filiereId(inscription.getFiliereId())
                .diplomat(inscription.getDiplomat())
                .build();
    }

    public List<InscriptionResponseLST> getAllInscriptionsLST() {
        return inscriptionRepository.findAllByDiplomat(Diplomat.LST).stream()
                .filter(inscription -> !inscription.isCanceled() && !inscription.isRefused() && !inscription.isAccepted())
                .map(this::mapInscriptionToInscriptionResponseLST)
                .toList();
    }

    private InscriptionResponseLST mapInscriptionToInscriptionResponseLST(Inscription inscription) {
        return InscriptionResponseLST.builder()
                .id(inscription.getId())
                .cin(inscription.getCin())
                .cne(inscription.getCne())
                .nom(inscription.getNom())
                .prenom(inscription.getPrenom())
                .genre(inscription.getGenre())
                .email(inscription.getEmail())
                .dateNaissance(inscription.getDateNaissance())
                .lieuNaissance(inscription.getLieuNaissance())
                .adresse(inscription.getAdresse())
                .telephone(inscription.getTelephone())
                .filiereBac(inscription.getFiliereBac())
                .moyenneBac(inscription.getMoyenneBac())
                .mentionBac(inscription.getMentionBac())
                .anneeBac(inscription.getAnneeBac())
                .etablissementBac(inscription.getEtablissementBac())
                .villeBac(inscription.getVilleBac())
                .paysBac(inscription.getPaysBac())
                .diplomeDeust(inscription.getDiplomeDeust())
                .moyenneDiplome(inscription.getMoyenneDiplome())
                .mentionDiplome(inscription.getMentionDiplome())
                .anneeDiplome(inscription.getAnneeDiplome())
                .etablissementDiplome(inscription.getEtablissementDiplome())
                .villeDiplome(inscription.getVilleDiplome())
                .paysDiplome(inscription.getPaysDiplome())
                .filiereId(inscription.getFiliereId())
                .diplomat(inscription.getDiplomat())
                .build();
    }

    public InscriptionResponseDEUST createInscriptionDEUST(InscriptionRequestDEUST inscriptionRequestDEUST) {
        inscriptionObjectsValidatorDEUST.validate(inscriptionRequestDEUST);
        Inscription inscription = mapInscriptionRequestDEUSTToInscription(inscriptionRequestDEUST);
        inscription.setDiplomat(Diplomat.DEUST);
        inscription.setCanceled(false);
        inscription.setAccepted(false);
        inscription.setRefused(false);
        inscriptionRepository.save(inscription);
        return mapInscriptionToInscriptionResponseDEUST(inscription);
    }

    private Inscription mapInscriptionRequestDEUSTToInscription(InscriptionRequestDEUST inscriptionRequestDEUST) {
        return Inscription.builder()
                .cin(inscriptionRequestDEUST.getCin())
                .cne(inscriptionRequestDEUST.getCne())
                .nom(inscriptionRequestDEUST.getNom())
                .prenom(inscriptionRequestDEUST.getPrenom())
                .email(inscriptionRequestDEUST.getEmail())
                .genre(inscriptionRequestDEUST.getGenre())
                .dateNaissance(inscriptionRequestDEUST.getDateNaissance())
                .lieuNaissance(inscriptionRequestDEUST.getLieuNaissance())
                .adresse(inscriptionRequestDEUST.getAdresse())
                .telephone(inscriptionRequestDEUST.getTelephone())
                .filiereBac(inscriptionRequestDEUST.getFiliereBac())
                .moyenneBac(inscriptionRequestDEUST.getMoyenneBac())
                .mentionBac(inscriptionRequestDEUST.getMentionBac())
                .anneeBac(inscriptionRequestDEUST.getAnneeBac())
                .etablissementBac(inscriptionRequestDEUST.getEtablissementBac())
                .villeBac(inscriptionRequestDEUST.getVilleBac())
                .paysBac(inscriptionRequestDEUST.getPaysBac())
                .filiereId(inscriptionRequestDEUST.getFiliereId())
                .diplomat(inscriptionRequestDEUST.getDiplomat())
                .build();
    }

    public InscriptionResponseLST createInscriptionLST(InscriptionRequestLST inscriptionRequestLST) {
        inscriptionRequestLSTObjectsValidator.validate(inscriptionRequestLST);
        Inscription inscription = mapInscriptionRequestToInscriptionLST(inscriptionRequestLST);
        inscription.setDiplomat(Diplomat.LST);
        inscription.setCanceled(false);
        inscription.setAccepted(false);
        inscription.setRefused(false);
        inscriptionRepository.save(inscription);
        return mapInscriptionToInscriptionResponseLST(inscription);
    }

    private Inscription mapInscriptionRequestToInscriptionLST(InscriptionRequestLST inscriptionRequestLST) {
        return Inscription.builder()
                .cin(inscriptionRequestLST.getCin())
                .cne(inscriptionRequestLST.getCne())
                .nom(inscriptionRequestLST.getNom())
                .prenom(inscriptionRequestLST.getPrenom())
                .email(inscriptionRequestLST.getEmail())
                .genre(inscriptionRequestLST.getGenre())
                .dateNaissance(inscriptionRequestLST.getDateNaissance())
                .lieuNaissance(inscriptionRequestLST.getLieuNaissance())
                .adresse(inscriptionRequestLST.getAdresse())
                .telephone(inscriptionRequestLST.getTelephone())
                .filiereBac(inscriptionRequestLST.getFiliereBac())
                .moyenneBac(inscriptionRequestLST.getMoyenneBac())
                .mentionBac(inscriptionRequestLST.getMentionBac())
                .anneeBac(inscriptionRequestLST.getAnneeBac())
                .etablissementBac(inscriptionRequestLST.getEtablissementBac())
                .villeBac(inscriptionRequestLST.getVilleBac())
                .paysBac(inscriptionRequestLST.getPaysBac())
                .diplomeDeust(inscriptionRequestLST.getDiplomeDeust())
                .moyenneDiplome(inscriptionRequestLST.getMoyenneDiplome())
                .mentionDiplome(inscriptionRequestLST.getMentionDiplome())
                .anneeDiplome(inscriptionRequestLST.getAnneeDiplome())
                .etablissementDiplome(inscriptionRequestLST.getEtablissementDiplome())
                .villeDiplome(inscriptionRequestLST.getVilleDiplome())
                .paysDiplome(inscriptionRequestLST.getPaysDiplome())
                .filiereId(inscriptionRequestLST.getFiliereId())
                .diplomat(inscriptionRequestLST.getDiplomat())
                .build();
    }

    public InscriptionResponseDEUST updateInscriptionDEUST(Long inscriptionId, InscriptionRequestDEUST inscriptionRequestDEUST) {
        inscriptionObjectsValidatorDEUST.validate(inscriptionRequestDEUST);
        log.info("Updating inscription with id {}", inscriptionId);
        Inscription inscription = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new InscriptionNotFoundException("Inscription not found"));


        inscription.setCin(inscriptionRequestDEUST.getCin());
        inscription.setCne(inscriptionRequestDEUST.getCne());
        inscription.setNom(inscriptionRequestDEUST.getNom());
        inscription.setPrenom(inscriptionRequestDEUST.getPrenom());
        inscription.setEmail(inscriptionRequestDEUST.getEmail());
        inscription.setDateNaissance(inscriptionRequestDEUST.getDateNaissance());
        inscription.setLieuNaissance(inscriptionRequestDEUST.getLieuNaissance());
        inscription.setAdresse(inscriptionRequestDEUST.getAdresse());
        inscription.setTelephone(inscriptionRequestDEUST.getTelephone());
        inscription.setFiliereBac(inscriptionRequestDEUST.getFiliereBac());
        inscription.setMoyenneBac(inscriptionRequestDEUST.getMoyenneBac());
        inscription.setMentionBac(inscriptionRequestDEUST.getMentionBac());
        inscription.setAnneeBac(inscriptionRequestDEUST.getAnneeBac());
        inscription.setEtablissementBac(inscriptionRequestDEUST.getEtablissementBac());
        inscription.setVilleBac(inscriptionRequestDEUST.getVilleBac());
        inscription.setPaysBac(inscriptionRequestDEUST.getPaysBac());
        inscription.setDiplomat(inscriptionRequestDEUST.getDiplomat());
        inscription.setFiliereId(inscriptionRequestDEUST.getFiliereId());

        Inscription savedInscription = inscriptionRepository.save(inscription);

        return mapInscriptionToInscriptionResponseDEUST(savedInscription);
    }

    public InscriptionResponseLST updateInscriptionLST(Long inscriptionId, InscriptionRequestLST inscriptionRequestLST) {
        inscriptionRequestLSTObjectsValidator.validate(inscriptionRequestLST);
        log.info("Updating inscription with id {}", inscriptionId);
        Inscription inscription = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new InscriptionNotFoundException("Inscription not found"));


        inscription.setCin(inscriptionRequestLST.getCin());
        inscription.setCne(inscriptionRequestLST.getCne());
        inscription.setNom(inscriptionRequestLST.getNom());
        inscription.setPrenom(inscriptionRequestLST.getPrenom());
        inscription.setEmail(inscriptionRequestLST.getEmail());
        inscription.setDateNaissance(inscriptionRequestLST.getDateNaissance());
        inscription.setLieuNaissance(inscriptionRequestLST.getLieuNaissance());
        inscription.setAdresse(inscriptionRequestLST.getAdresse());
        inscription.setTelephone(inscriptionRequestLST.getTelephone());
        inscription.setFiliereBac(inscriptionRequestLST.getFiliereBac());
        inscription.setMoyenneBac(inscriptionRequestLST.getMoyenneBac());
        inscription.setMentionBac(inscriptionRequestLST.getMentionBac());
        inscription.setAnneeBac(inscriptionRequestLST.getAnneeBac());
        inscription.setEtablissementBac(inscriptionRequestLST.getEtablissementBac());
        inscription.setVilleBac(inscriptionRequestLST.getVilleBac());
        inscription.setPaysBac(inscriptionRequestLST.getPaysBac());
        inscription.setDiplomeDeust(inscriptionRequestLST.getDiplomeDeust());
        inscription.setMoyenneDiplome(inscriptionRequestLST.getMoyenneDiplome());
        inscription.setMentionDiplome(inscriptionRequestLST.getMentionDiplome());
        inscription.setAnneeDiplome(inscriptionRequestLST.getAnneeDiplome());
        inscription.setEtablissementDiplome(inscriptionRequestLST.getEtablissementDiplome());
        inscription.setVilleDiplome(inscriptionRequestLST.getVilleDiplome());
        inscription.setPaysDiplome(inscriptionRequestLST.getPaysDiplome());
        inscription.setDiplomat(inscriptionRequestLST.getDiplomat());
        inscription.setFiliereId(inscriptionRequestLST.getFiliereId());

        Inscription savedInscription = inscriptionRepository.save(inscription);

        return mapInscriptionToInscriptionResponseLST(savedInscription);
    }
}

