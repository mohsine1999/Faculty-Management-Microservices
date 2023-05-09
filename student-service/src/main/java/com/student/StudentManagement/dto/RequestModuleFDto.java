package com.student.StudentManagement.dto;

import com.student.StudentManagement.model.Filiere;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestModuleFDto {

    private String name ;
    //
    private Filiere filiere;


}
