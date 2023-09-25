package com.example.securityoauth2.service;

import com.example.securityoauth2.Dto.RoleDto;
import com.example.securityoauth2.Dto.UserDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import com.example.securityoauth2.Repository.AppRoleRepository;
import com.example.securityoauth2.Repository.AppUserRepository;
import com.example.securityoauth2.entities.AppRole;
import com.example.securityoauth2.entities.AppUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Slf4j
@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    public AppUserServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public RoleDto addRole(RoleDto roleDto) {
        AppRole byRoleName = appRoleRepository.findByRoleName(roleDto.getRoleName());
        if(byRoleName != null ){
            throw new EntityExistsException(String.format("this role : %s exist",roleDto.getRoleName()));
        }
        AppRole role=new AppRole();
        BeanUtils.copyProperties(roleDto,role);

        byRoleName=appRoleRepository.save(role);
        BeanUtils.copyProperties(byRoleName,roleDto);
        return roleDto;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        AppUser byUsername = appUserRepository.findByUsername(userDto.getUsername());
        if(byUsername != null ){
            throw new EntityExistsException(String.format("this User : %s exist",userDto.getUsername()));
        }
        String ps =userDto.getPassword();
        userDto.setPassword(passwordEncoder.encode(ps));
        AppUser appUser=new AppUser();
        BeanUtils.copyProperties(userDto,appUser);
         byUsername=appUserRepository.save(appUser);
         BeanUtils.copyProperties(byUsername,userDto);
         return  userDto;
    }

    @Override
    public void addRoleToUser(String nameUser, String roleName) {
        AppUser user= appUserRepository.findByUsername(nameUser);
        AppRole role= appRoleRepository.findByRoleName(roleName);
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.getAppRoles().add(role);
    }

    @Override
    public AppUser loadUserByName(String name) {
        if(name == null || name.equals("")){
            throw  new EntityNotFoundException("username is null  ");
        }
         return appUserRepository.findByUsername(name);

    }

    @Override
    public UserDto registerNewStudent(UserDto userDto) {
        AppUser appUser1 = loadUserByName(userDto.getUsername());
        if(appUser1 != null ) {
            throw new EntityExistsException("user exist");
        }
        if(userDto.getPassword() ==null ||userDto.getPassword().length() < 4 )
        {
            throw new  EntityExistsException("length of password < 4");
        }
        UserDto userDto1 = addUser(userDto);
        Collection<RoleDto> roles = userDto.getAppRoles();
        // on suposser les rolles est selectionner appartir du bdd ;
        RoleDto appRole=new RoleDto(null,"STUDENT");
        AppUser appUser = new AppUser();

        AppRole byRoleName = appRoleRepository.findByRoleName(appRole.getRoleName());
        if(byRoleName==null ) {
            appRole=addRole(appRole);
        }
        addRoleToUser(userDto1.getUsername(),appRole.getRoleName());
            BeanUtils.copyProperties(userDto1,appUser);
            
        userDto.setPassword(null);
        return userDto;
    }

    @Override
    public List<UserDto> listUser() {
        List<AppUser> allUser = appUserRepository.findAll();
        UserDto userDto=new UserDto();
        List<UserDto> userDtoList=new ArrayList<>();
        allUser.forEach( appUser -> {
            BeanUtils.copyProperties(appUser,userDto);
            userDtoList.add(userDto);
        }
        );
        return userDtoList;
    }

    @Override
    public List<RoleDto> lisRolle() {
        List<AppRole> allUser = appRoleRepository.findAll();
        RoleDto roleDto=new RoleDto();
        List<RoleDto> roleDtoList=new ArrayList<>();
        allUser.forEach( appUser -> {
                    BeanUtils.copyProperties(appUser,roleDto);
                    roleDtoList.add(roleDto);
                }
        );
        return roleDtoList;
    }
}
