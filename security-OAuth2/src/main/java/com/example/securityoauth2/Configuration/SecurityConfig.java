package com.example.securityoauth2.Configuration;

import com.example.securityoauth2.service.MyUserDetailService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig   {
    private final PasswordEncoder passwordEncoder;
    private final MyUserDetailService userDetailService;
    private final  RsakeysConfig rsakeysConfig;


    @Bean
    public AuthenticationManager authenticationManager(){
        var authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailService);
        System.out.println("user $$"+authenticationProvider.getUserCache());
        return  new ProviderManager(authenticationProvider);
    }

     //@Bean
    public UserDetailsService inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode("1111")).authorities("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("1111")).authorities("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("1111")).authorities("ADMIN","USER").build()
                );
    }
    /*
    //@Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenProvider=new DaoAuthenticationProvider();
       // authenProvider.setUserDetailsService(userDetailService);
        authenProvider.setPasswordEncoder(passwordEncoder);
        return  authenProvider;
    }
*/

    @Bean
    @Order(1)
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors ->
                        cors.configurationSource(request ->
                                new CorsConfiguration().applyPermitDefaultValues())
                )

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("http://localhost:9090/security-service/api/auth/**","/api/auth/**","/api/hello/**","/actuator/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(session -> session
                      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .httpBasic(Customizer.withDefaults())

                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                      httpSecurityExceptionHandlingConfigurer
                              .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .cors(Customizer.withDefaults())
                .build();
                //.rememberMe(Customizer.withDefaults())
                //.authenticationProvider(authenticationProvider())
                //.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
               // .formLogin(httpSecurityFormLoginConfigurer ->
                 //       httpSecurityFormLoginConfigurer.loginPage("/login")
                //)

    }

    // nous besoi deux clee prive et public il faut signer les token avec 'eresa' pour le gererr openSSL OU BIEN JAVA
    // ligne de commande ans resource cre doucier sertificat
    //1 cmd openssl genrsa -out keypair.pim 2048
    //2 eme public key> openssl rsa -in keypair.pem -pubout -out public.pem
    //3 eme private key sous forme pk> openssl pkcs8 -topk8 -inform PEM -nocrypt -in keypair.pem -out private.pem
  @Bean
   public JwtEncoder jwtEncoder(){
       JWK jwk=new RSAKey.Builder(rsakeysConfig.publickey())
                         .privateKey(rsakeysConfig.privatekey())
                          .build();
       JWKSource<SecurityContext> jwkSource=new ImmutableJWKSet<>(new JWKSet(jwk));
       return new NimbusJwtEncoder(jwkSource);
   }
   @Bean
   public JwtDecoder jwtDecoder(){
       System.out.println("******************** hello *********"+rsakeysConfig.publickey());
       NimbusJwtDecoder build = NimbusJwtDecoder
               .withPublicKey(rsakeysConfig.publickey())
               .build();
       return  build;
   }

    //@Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setExposedHeaders (List.of("x-auth-token"));
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
