package com.radouaneoubakhane.serviceinscription.model.enums;

public enum Mention {
    TRES_BIEN("Très Bien"),
    BIEN("Bien"),
    ASSEZ_BIEN("Assez Bien"),
    PASSABLE("Passable"),
    INSUFFISANT("Insuffisant");

    private String mention;

    Mention(String mention) {
        this.mention = mention;
    }

    public String getMention() {
        return mention;
    }
}
