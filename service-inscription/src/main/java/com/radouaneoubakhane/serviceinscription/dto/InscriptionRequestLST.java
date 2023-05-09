package com.radouaneoubakhane.serviceinscription.dto;


import com.radouaneoubakhane.serviceinscription.annotations.EnumNamePattern;
import com.radouaneoubakhane.serviceinscription.model.enums.*;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InscriptionRequestLST {
    //    LES INFORMARTIONS PERSONNELLES

    @NotBlank(message = "Le CIN est obligatoire")
    private String cin;

    @NotBlank(message = "Le CNE est obligatoire")
    private String cne;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prenom est obligatoire")
    private String prenom;


    @EnumNamePattern(
            regexp = "MALE|FEMALE",
            message = "Le genre doit etre valide"
    )
    @Enumerated(EnumType.STRING)
    private Gender genre ;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit etre valide")
    private String email;

    @Past(message = "La date de naissance doit etre dans le passe")
    private Date dateNaissance;


    @NotBlank(message = "Le lieu de naissance est obligatoire")
    private String lieuNaissance;

    @NotBlank(message = "L'addresse est obligatoire")
    private String adresse;

    @NotBlank(message = "Le telephone est obligatoire")
    private String telephone;


    //    LES INFORMARTIONS DU BACCALAUREAT
    @EnumNamePattern(
            regexp = "SCIENCES_MATHS|SCIENCES_EXP|SCIENCES_TECH|SCIENCES_ECO|SCIENCES_GESTION|LETTRES|ARTS|SPORT",
            message = "Le type de bac doit etre valide"
    )
    @Enumerated(EnumType.STRING)
    private Bac filiereBac;


    @NotNull(message = "La moyenne du bac est obligatoire")
    @Positive(message = "La moyenne du bac doit etre positive")
    @Max(value = 20, message = "La moyenne du bac doit etre inferieure a 20")
    @Min(value = 0, message = "La moyenne du bac doit etre superieure a 0")
    private double moyenneBac;


    @EnumNamePattern(
            regexp = "TRES_BIEN|BIEN|ASSEZ_BIEN|PASSABLE|INSUFFISANT",
            message = "La mention du bac doit etre valide"
    )
    @Enumerated(EnumType.STRING)
    private Mention mentionBac;


    @NotBlank(message = "L'annee du bac est obligatoire")
    @Column(name = "annee_bac")
    private String anneeBac;


    @NotBlank(message = "L'etablissement du bac est obligatoire")
    private String etablissementBac;


    @NotBlank(message = "La ville du bac est obligatoire")
    private String villeBac;


    @NotBlank(message = "Le pays du bac est obligatoire")
    private String paysBac;

    //    LES INFORMATIONS DU DIPLOME DEUST
    @EnumNamePattern(
            regexp = "MIP|BCG|GE|GM",
            message = "Le diplome deust doit etre valide"
    )
    @Enumerated(EnumType.STRING)
    private DiplomeDEUST diplomeDeust;


    @NotNull(message = "La moyenne du diplome deust est obligatoire")
    @Positive(message = "La moyenne du diplome deust doit etre positive")
    @Max(value = 20, message = "La moyenne du diplome deust doit etre inferieure a 20")
    @Min(value = 0, message = "La moyenne du diplome deust doit etre superieure a 0")
    private double moyenneDiplome;



    @Enumerated(EnumType.STRING)
    @EnumNamePattern(
            regexp = "TRES_BIEN|BIEN|ASSEZ_BIEN|PASSABLE|INSUFFISANT",
            message = "La mention du diplome deust doit etre valide"
    )
    @Column(name = "mention_diplome")
    private Mention mentionDiplome;


    @NotBlank(message = "L'annee du diplome deust est obligatoire")
    private String anneeDiplome;


    @NotBlank(message = "L'etablissement du diplome deust est obligatoire")
    private String etablissementDiplome;


    @NotBlank(message = "La ville du diplome deust est obligatoire")
    private String villeDiplome;


    @NotBlank(message = "Le pays du diplome deust est obligatoire")
    private String paysDiplome;


    // Diplomat
    @NotNull(message = "Le diplomant est obligatoire")
    @EnumNamePattern(
            regexp = "DEUST|LST|MST",
            message = "Le diplomant doit etre valide"
    )
    @Enumerated(EnumType.STRING)
    private Diplomat diplomat;

    //    La FILIERE
    @Positive(message = "La filiere est obligatoire")
    @NotNull(message = "La filiere est obligatoire")
    @Column(name = "filiere_id")
    private Long filiereId;
}
