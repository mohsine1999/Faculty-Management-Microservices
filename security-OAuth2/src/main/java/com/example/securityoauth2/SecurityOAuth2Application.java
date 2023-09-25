package com.example.securityoauth2;

import com.example.securityoauth2.Configuration.RsakeysConfig;
import com.example.securityoauth2.Dto.RoleDto;
import com.example.securityoauth2.Dto.UserDto;
import com.example.securityoauth2.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication

@EnableConfigurationProperties(RsakeysConfig.class)
public class SecurityOAuth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SecurityOAuth2Application.class, args);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
 // @Bean
    CommandLineRunner start(AppUserService appUserService){
        return  args -> {

            UserDto userDto= new UserDto("outman","1234");
             userDto = appUserService.addUser(userDto);

            UserDto userDto1= new UserDto("miraouy","1234");
                    userDto1 = appUserService.addUser(userDto1);

            RoleDto roleDto=new RoleDto(null,"USER");
                     roleDto=appUserService.addRole(roleDto);
            RoleDto roleDto1=new RoleDto(null,"ADMIN");
                     roleDto1=appUserService.addRole(roleDto1);

            userDto.getAppRoles().add(roleDto);
            userDto.getAppRoles().add(roleDto1);
            appUserService.addRoleToUser(userDto.getUsername(), roleDto.getRoleName());
            appUserService.addRoleToUser(userDto.getUsername(), roleDto1.getRoleName());

            userDto1.getAppRoles().add(roleDto1);
            appUserService.addRoleToUser(userDto1.getUsername(), roleDto.getRoleName());

            RoleDto roleDto3=new RoleDto(null,"DIRECTOR");
            roleDto=appUserService.addRole(roleDto3);
            RoleDto roleDto4=new RoleDto(null,"STUDENT");
            roleDto=appUserService.addRole(roleDto4);
            RoleDto roleDto5=new RoleDto(null,"TEACHER");
            roleDto=appUserService.addRole(roleDto5);
        } ;
    }
}
