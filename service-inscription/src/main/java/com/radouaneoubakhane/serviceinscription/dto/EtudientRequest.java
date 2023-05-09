package com.radouaneoubakhane.serviceinscription.dto;


import com.radouaneoubakhane.serviceinscription.model.enums.Gender;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EtudientRequest {
    private String cin ;
    private Long apogee;
    private String nom ;
    private String prenom ;
    private String cne ;
    private String email ;
    private String phone;
    private Date dateNaissance ;
    private String lieuNaissance ;
    private String adresse ;
    private Gender genre ;
    private Long idFiliere;
}
