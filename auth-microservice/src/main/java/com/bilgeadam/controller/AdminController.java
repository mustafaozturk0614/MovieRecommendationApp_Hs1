package com.bilgeadam.controller;

import com.bilgeadam.dto.request.ActivateRequestDto;
import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.request.UpdateEmailAndUsernameRequestDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.entity.Auth;
import com.bilgeadam.repository.AuthRepository;
import com.bilgeadam.service.AuthService;
import com.bilgeadam.utility.JwtTokenManager;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.bilgeadam.constant.EndPoints.*;
import static com.bilgeadam.constant.EndPoints.UPDATE;

@RestController

@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void init() {
        if (authService == null || jwtTokenManager == null || cacheManager == null) {
            throw new IllegalStateException("Dependencies are not properly injected!");
        }
    }

    @PostMapping(REGISTER) //api/v1/auth/register
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    // activate activasyon kodu ile  authun statusunu pending den active e cekecek
    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<String> activateStatus(@RequestBody ActivateRequestDto dto) {
        return ResponseEntity.ok(authService.activateStatus(dto));
    }
    // token olusturma ve verify etmek için deneme amaclı end pointler

    @GetMapping("/create-token-by-id")
    private Optional<String> creteToken(Long id) {

        try {
        return     jwtTokenManager.createToken(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @GetMapping("/create-token-by-id-and-role")
    private Optional<String> creteToken(Long id,String role) {

        System.out.println(jwtTokenManager);
        return jwtTokenManager.createToken(id,role);
    }

    @GetMapping("/get-id-by-token")
    private Optional<Long> getIdByToken(String token) {

        return jwtTokenManager.getAuthIdFromToken(token);
    }

    @GetMapping("/get-role-by-token")
    private Optional<String> getRoleByToken(String token) {
        return jwtTokenManager.getRoleFromToken(token);
    }

    @PostMapping(LOGIN)
    private ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> updateEmailAndUsername(@RequestBody @Valid UpdateEmailAndUsernameRequestDto dto){
        return ResponseEntity.ok(authService.updateEmailAndUsername(dto));
    }




}
