package com.example.securityoauth2.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RoleDto {

    protected Long id ;

    private String roleName;


    public RoleDto(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
}
