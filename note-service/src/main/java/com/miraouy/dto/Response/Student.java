package com.miraouy.dto.Response;

import lombok.Data;

@Data
public class Student {
    private Long apogee;
    private String nom;
    private String prenom;
    private String cne;
    //private ModuleF module;
    private Filiere filiere;


}
