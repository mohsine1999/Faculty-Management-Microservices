package com.example.securityoauth2.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
public class UserDto {
        private String username;
        private String password;
        private String confirmPassword;
        private String rolename;
        private Collection<RoleDto> appRoles =new ArrayList<>() ;
        public UserDto(String username,String password){
                this.username=username;
                this.password=password;
                this.appRoles =  new ArrayList<>();
        }

}

