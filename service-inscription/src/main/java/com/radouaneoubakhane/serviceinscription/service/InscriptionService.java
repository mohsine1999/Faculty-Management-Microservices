package com.radouaneoubakhane.serviceinscription.service;


import com.radouaneoubakhane.serviceinscription.dto.EtudientRequest;
import com.radouaneoubakhane.serviceinscription.dto.FilierResponse;
import com.radouaneoubakhane.serviceinscription.dto.InscriptionRequest;
import com.radouaneoubakhane.serviceinscription.dto.InscriptionResponse;
import com.radouaneoubakhane.serviceinscription.exception.FiliereNotFoundException;
import com.radouaneoubakhane.serviceinscription.exception.InscriptionAlreadyAcceptedOrRefusedOrCanceledException;
import com.radouaneoubakhane.serviceinscription.exception.InscriptionNotFoundException;
import com.radouaneoubakhane.serviceinscription.model.Inscription;
import com.radouaneoubakhane.serviceinscription.model.enums.Diplomat;
import com.radouaneoubakhane.serviceinscription.openfeingClients.EtudientClient;
import com.radouaneoubakhane.serviceinscription.openfeingClients.FiliereClient;
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
    private final FiliereClient filiereClient;
    private final EtudientClient etudientClient;
    private final ObjectsValidator<InscriptionRequest> objectsValidator;


    public List<InscriptionResponse> getAllInscriptions() {
        log.info("Getting all inscriptions");
        List<Inscription> inscriptions = inscriptionRepository.findAll();
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
        FilierResponse filier = filiereClient.getFilierById(inscriptionRequest.getFiliereId());
        if (filier == null) {
            throw new FiliereNotFoundException("Filiere not found");
        }
        
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

        FilierResponse filier = filiereClient.getFilierById(inscriptionRequest.getFiliereId());
        if (filier == null) {
            throw new FiliereNotFoundException("Filiere not found");
        }

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
                .toList();;


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
                .toList();;

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

}
