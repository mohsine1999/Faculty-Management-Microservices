package com.student.StudentManagement.model;

import com.student.StudentManagement.enumurations.Diplomat;
import com.student.StudentManagement.enumurations.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentPojo {
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
    @Enumerated(EnumType.STRING)
    private Gender genre ;
    private Long idFiliere;

}
