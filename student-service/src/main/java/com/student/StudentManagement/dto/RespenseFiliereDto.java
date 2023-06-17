package com.student.StudentManagement.dto;

import com.student.StudentManagement.enumurations.Diplomat;
import com.student.StudentManagement.model.ModuleF;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespenseFiliereDto {
    private Long id ;
    private String name ;
    private Diplomat diplomat;
    private List<ModuleF> modules;
}
