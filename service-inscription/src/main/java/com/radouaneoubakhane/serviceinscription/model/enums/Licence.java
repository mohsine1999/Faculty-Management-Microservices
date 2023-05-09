package com.radouaneoubakhane.serviceinscription.model.enums;

public enum Licence {
    GM("Génie Mathématique"),
    IET("Ingénierie Electronique et Télécommunication"),
    IIEA("Ingénierie Informatique Electronique et Automatique"),
    PE("Protection de l’environnement"),
    INFO("Informatique"),
    GAT("Géomatique et Aménagement du Territoire"),
    SBAB("Sciences Biologiques Appliquées Biomédicale"),
    TQPA("Technologie et Qualité des Produits Agroalimentaires"),
    CAOGCM("Chimie Appliquée Option : Génie Chimie des Matériaux"),
    CAOTACQ("Chimie Appliquée Option : Techniques d’Analyses et Contrôle Qualité");


    private String licence;

    Licence(String licence) {
        this.licence = licence;
    }

    public String getLicence() {
        return licence;
    }
}
