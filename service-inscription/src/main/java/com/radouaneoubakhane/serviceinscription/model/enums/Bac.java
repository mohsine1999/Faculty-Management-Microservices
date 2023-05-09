package com.radouaneoubakhane.serviceinscription.model.enums;

public enum Bac {
    SCIENCES_MATHS("Sciences Mathématiques"),
    SCIENCES_EXP("Sciences Expérimentales"),
    SCIENCES_TECH("Sciences et Technologies"),
    SCIENCES_ECO("Sciences Économiques"),
    SCIENCES_GESTION("Sciences de Gestion"),
    LETTRES("Lettres"),
    ARTS("Arts"),
    SPORT("Sport");

    private String bac;

    Bac(String bac) {
        this.bac = bac;
    }

    public String getBac() {
        return bac;
    }

}
