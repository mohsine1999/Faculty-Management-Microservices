package com.example.securityoauth2.controller;

import com.example.securityoauth2.Dto.UserDto;
import com.example.securityoauth2.service.AppUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// Authentification Basic

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthentiController {

    private final JwtEncoder jwtEncoder;

    private  final JwtDecoder jwtDecoder;
    private final UserDetailsService userDetailsService;
    private final  AuthenticationManager authenticationManager  ;
    private final AppUserService appUserService;

    @PostMapping("/token")
    public ResponseEntity<Map<String,String> > jwtToken(String grantType,
                                                        String username, String password
                                                       , boolean withRefreshToken,
                                                       String refreshToken){
        String subject=null;
        String scope=null;
        if(grantType.equals("password")) {
            System.out.println("****** authentication with  password**");
            Authentication  authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(username, password)
                    );
            subject=authentication.getName();
            System.out.println("******* "+subject);
            scope=authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
            System.out.println("******* "+scope);

        } else if (grantType.equals("refreshToken")) {
            if(refreshToken==null) {
                return  new ResponseEntity<>(Map.of("ErrorMesaage","RefresheToken Is Required"), HttpStatus.UNAUTHORIZED);
            }

            Jwt decodeJwt= null;
            try {
                decodeJwt = jwtDecoder.decode(refreshToken);
            } catch (JwtException e) {
                return  new ResponseEntity<>(Map.of("ErrorMesaage",e.getMessage()), HttpStatus.UNAUTHORIZED);
            }
            System.out.println("code-------"+decodeJwt);
            subject=decodeJwt.getSubject();
            UserDetails userDetails= userDetailsService.loadUserByUsername(subject);
            System.out.println("user----"+subject);
            Collection<? extends GrantedAuthority> authorities=userDetails.getAuthorities();
            scope=authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        }
        else {
            return  new ResponseEntity<>(Map.of("ErrorMesaage","grant type not supported"), HttpStatus.UNAUTHORIZED);
        }

        Map<String,String> tokenPersonnaliser=new HashMap<>();
        Instant instant=Instant.now();
        if(subject== null ){
            return  new ResponseEntity<>(Map.of("erreurMessage","username is null"),HttpStatus.NO_CONTENT);
        }
        JwtClaimsSet jwtClaimsSet= JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(withRefreshToken?3:5, ChronoUnit.MINUTES))
                .issuer("security-service") // lien http vers la microservice
                .claim("scope",scope)
                .build();

        String jwtAccestoken =jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        tokenPersonnaliser.put("accessToken",jwtAccestoken);
        if(withRefreshToken){
            JwtClaimsSet jwtClaimsSetref= JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(30, ChronoUnit.MINUTES))
                    .issuer("securite-service")
                    .build();
            String jwtRefreshToken =jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
            tokenPersonnaliser.put("refreshToken",jwtRefreshToken);
        }
        return new  ResponseEntity<>(tokenPersonnaliser,HttpStatus.OK);
    }

    @PostMapping("/tokenBasic")
  public Map<String,String> jwtToken(Authentication authentication){
        Map<String,String> idtoken=new HashMap<>();

        Instant instant=Instant.now();
       String scope=authentication.getAuthorities()
               .stream()
               .map(GrantedAuthority::getAuthority)
               .collect(Collectors.joining(" "));
        JwtClaimsSet jwtClaimsSet= JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuedAt(instant)
                .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
                .issuer("security-service")
                .claim("scope",scope)
                .build();

        String jwtAccessToken =jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        idtoken.put("accessToken",jwtAccessToken);
        return idtoken;
    }
    @GetMapping("/profile")
    public ResponseEntity<Authentication> profile( Authentication authentication){
        return ResponseEntity.ok(authentication);

    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(
            @RequestBody UserDto userDto
            ){
        return ResponseEntity.ok(appUserService.registerNewStudent(userDto));
    }


}
