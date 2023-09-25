package com.example.securityoauth2.service;


import com.example.securityoauth2.Dto.RoleDto;
import com.example.securityoauth2.Dto.UserDto;
import com.example.securityoauth2.entities.AppRole;
import com.example.securityoauth2.entities.AppUser;

import java.util.List;


public interface AppUserService {
    UserDto addUser(UserDto USER);
    RoleDto addRole(RoleDto role);
    void addRoleToUser(String nameOfuser,String Rolename);
    AppUser loadUserByName(String name);
    UserDto registerNewStudent(UserDto userDto);
    List<UserDto> listUser();
    List<RoleDto> lisRolle();
}
