package com.radouaneoubakhane.serviceinscription.model.enums;

public enum DiplomeDEUST {
    MIP("Mathématiques Informatique Physique"),
    BCG("Biologie - Chimie - Géologie"),
    GE("Génie Electrique"),
    GM("Génie Mécanique");

    private String diplomeDEUST;

    DiplomeDEUST(String diplomeDEUST) {
        this.diplomeDEUST = diplomeDEUST;
    }

    public String getDiplomeDEUST() {
        return diplomeDEUST;
    }
}
