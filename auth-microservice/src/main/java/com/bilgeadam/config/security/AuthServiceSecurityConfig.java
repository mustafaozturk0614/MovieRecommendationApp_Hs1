package com.bilgeadam.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class AuthServiceSecurityConfig {

   private final JwtTokenFilter getJwtTokenFilter;

   private static  final  String [] WHITELIST = {
           "/swagger-ui/**", "/v3/api-docs/**", "/api/v1/auth/Login", "/api/v1/auth/register",
           "/api/v1/auth/activate_status","/api/v1/auth/find_all","/auth/find_all"
   };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeHttpRequests(req->
                req.requestMatchers(WHITELIST).permitAll()
                        .requestMatchers("/api/v1/auth/find_by_id").hasAuthority("ADMIN")
                        .requestMatchers("/api/v1/auth/get-current-auth").hasAuthority("USER")
                        .anyRequest().authenticated()
        );


        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.addFilterBefore(getJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
