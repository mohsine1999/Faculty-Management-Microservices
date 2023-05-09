package com.student.StudentManagement.dto;

import com.student.StudentManagement.enumurations.Gender;
import com.student.StudentManagement.model.Carriere;
import com.student.StudentManagement.model.Filiere;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespenseStudentDto {
    private String cin ;
    private long apogee;
    private String nom ;
    private String prenom ;
    private String cne ;
    private String email ;
    private Gender genre ;
    private Filiere filiere;
    private List<Carriere> carriere;
}
