package com.radouaneoubakhane.serviceinscription.dto;


import com.radouaneoubakhane.serviceinscription.model.enums.*;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InscriptionResponse {
    private Long id;
    //    LES INFORMARTIONS PERSONNELLES
    private String cin;
    private String cne;
    private String nom;
    private String prenom;
    private Gender genre;
    private String email;

    private Date dateNaissance;
    private String lieuNaissance;
    private String adresse;
    private String telephone;

    //    LES INFORMARTIONS DU BACCALAUREAT
    @Enumerated(EnumType.STRING)
    private Bac filiereBac;
    private double moyenneBac;
    @Enumerated(EnumType.STRING)
    private Mention mentionBac;
    private String anneeBac;
    private String etablissementBac;
    private String villeBac;
    private String paysBac;

    //    LES INFORMATIONS DU DIPLOME DEUST
    @Enumerated(EnumType.STRING)
    private DiplomeDEUST diplomeDeust;
    private double moyenneDiplome;
    @Enumerated(EnumType.STRING)
    private Mention mentionDiplome;
    private String anneeDiplome;
    private String etablissementDiplome;
    private String villeDiplome;
    private String paysDiplome;

    //    LES INFORMARTIONS DE LA LICENCE
    @Enumerated(EnumType.STRING)
    private Licence licence;
    private double moyenneLicence;
    @Enumerated(EnumType.STRING)
    private Mention mentionLicence;
    private String anneeLicence;
    private String etablissementLicence;
    private String villeLicence;
    private String paysLicence;

    // Diplomat
    private Diplomat diplomat;

    //    La FILIERE
    private Long filiereId;
}
