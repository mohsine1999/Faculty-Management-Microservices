package com.radouaneoubakhane.serviceinscription.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespenseFiliereDto {
    private Long id;
    private String name ;
    //private List<ModuleF> modules;
}
