package com.student.StudentManagement.model;

import com.student.StudentManagement.enumurations.Diplomat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CarrierePojo {


    @Enumerated(EnumType.STRING)
    private Diplomat diplomat ;
    private Long studentId;
}
