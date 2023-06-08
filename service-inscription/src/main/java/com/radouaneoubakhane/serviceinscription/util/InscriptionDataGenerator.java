package com.radouaneoubakhane.serviceinscription.util;

import com.radouaneoubakhane.serviceinscription.model.Inscription;
import com.radouaneoubakhane.serviceinscription.model.enums.*;

import java.time.LocalDate;
import java.util.Date;

public class InscriptionDataGenerator {
    public static Inscription generateInscription() {

        return Inscription.builder()
                        .nom("Radouane")
                        .prenom("Radouane")
                        .genre(Gender.MALE)
                        .dateNaissance(Date.from(LocalDate.of(1995, 8, 10).atStartOfDay().toInstant(java.time.ZoneOffset.UTC)))
                        .lieuNaissance("Casablanca")
                        .adresse("Casablanca")
                        .telephone("0600000000")
                        .cin("EE000000")
                        .cne("G000000")
                        .email("radouane@gmai.com")
                        .filiereBac(Bac.SCIENCES_MATHS)
                        .moyenneBac(15.5)
                        .moyenneDiplome(16.5)
                        .moyenneLicence(17.5)
                        .mentionBac(Mention.BIEN)
                        .anneeBac("2013")
                        .etablissementBac("Lycée")
                        .villeBac("Casablanca")
                        .paysBac("Maroc")
                        .diplomeDeust(DiplomeDEUST.MIP)
                        .mentionDiplome(Mention.BIEN)
                        .anneeDiplome("2015")
                        .etablissementDiplome("Faculté")
                        .villeDiplome("Casablanca")
                        .paysDiplome("Maroc")
                        .licence(Licence.INFO)
                        .mentionLicence(Mention.BIEN)
                        .anneeLicence("2018")
                        .etablissementLicence("Faculté")
                        .villeLicence("Casablanca")
                        .paysLicence("Maroc")
                        .isAccepted(false)
                        .isRefused(false)
                        .isCanceled(false)
                        .diplomat(Diplomat.MST)
                        .filiereId(1L)
                        .build();
    }

    public static Inscription generateInscriptionDeus() {

        return Inscription.builder()
                .nom("Radouane")
                .prenom("Radouane")
                .genre(Gender.MALE)
                .dateNaissance(Date.from(LocalDate.of(1995, 8, 10).atStartOfDay().toInstant(java.time.ZoneOffset.UTC)))
                .lieuNaissance("Casablanca")
                .adresse("Casablanca")
                .telephone("0600000000")
                .cin("EE000000")
                .cne("G000000")
                .email("radouane@gmai.com")
                .filiereBac(Bac.SCIENCES_MATHS)
                .moyenneBac(15.5)
                .mentionBac(Mention.BIEN)
                .anneeBac("2013")
                .etablissementBac("Lycée")
                .villeBac("Casablanca")
                .paysBac("Maroc")
                .isAccepted(false)
                .isRefused(false)
                .isCanceled(false)
                .diplomat(Diplomat.DEUST)
                .filiereId(1L)
                .build();
    }

    public static Inscription generateInscriptionLST() {

        return Inscription.builder()
                .nom("Radouane")
                .prenom("Radouane")
                .genre(Gender.MALE)
                .dateNaissance(Date.from(LocalDate.of(1995, 8, 10).atStartOfDay().toInstant(java.time.ZoneOffset.UTC)))
                .lieuNaissance("Casablanca")
                .adresse("Casablanca")
                .telephone("0600000000")
                .cin("EE000000")
                .cne("G000000")
                .email("radouane@gmai.com")
                .filiereBac(Bac.SCIENCES_MATHS)
                .moyenneBac(15.5)
                .moyenneDiplome(16.5)
                .mentionBac(Mention.BIEN)
                .anneeBac("2013")
                .etablissementBac("Lycée")
                .villeBac("Casablanca")
                .paysBac("Maroc")
                .diplomeDeust(DiplomeDEUST.MIP)
                .mentionDiplome(Mention.BIEN)
                .anneeDiplome("2015")
                .etablissementDiplome("Faculté")
                .villeDiplome("Casablanca")
                .paysDiplome("Maroc")
                .isAccepted(false)
                .isRefused(false)
                .isCanceled(false)
                .diplomat(Diplomat.LST)
                .filiereId(1L)
                .build();
    }
}
