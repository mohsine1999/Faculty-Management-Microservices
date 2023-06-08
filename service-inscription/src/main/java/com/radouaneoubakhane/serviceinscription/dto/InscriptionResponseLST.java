package com.radouaneoubakhane.serviceinscription.dto;


import com.radouaneoubakhane.serviceinscription.model.enums.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InscriptionResponseLST {
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

    // Diplomat
    private Diplomat diplomat;

    //    La FILIERE
    private Long filiereId;
}
