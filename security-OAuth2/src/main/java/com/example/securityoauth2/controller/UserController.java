package com.example.securityoauth2.controller;

import com.example.securityoauth2.Dto.RoleDto;
import com.example.securityoauth2.Dto.UserDto;
import com.example.securityoauth2.service.AppUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
public class UserController {
    private  final AppUserService appUserService;


    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> findallUser()
    {
        return  ResponseEntity.ok(appUserService.listUser());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDto>> findallRole(){
        return  ResponseEntity.ok(appUserService.lisRolle());
    }

    @PostMapping("/role")
    public ResponseEntity<RoleDto> saverole(@RequestBody  RoleDto role){
        return  ResponseEntity.ok(appUserService.addRole(role));
    }

    @GetMapping("/profile")
    public ResponseEntity<Authentication> authentication(Authentication authentication){
        return ResponseEntity.ok(authentication);
    }

    @GetMapping("/hello")
    public Map<String,String> hello(){

        return Map.of("hello","mam");
    }
}
