package com.bilgeadam.controller;

import static  com.bilgeadam.constant.EndPoints.*;

import com.bilgeadam.dto.request.ActivateRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.entity.Auth;
import com.bilgeadam.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)// api/v1/auth
public class AuthController {

    private final AuthService authService;

    @PostMapping(REGISTER) //api/v1/auth/register
    public ResponseEntity<RegisterResponseDto> register(@RequestBody  @Valid RegisterRequestDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    // activate activasyon kodu ile  authun statusunu pending den active e cekecek
    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<String> activateStatus(@RequestBody ActivateRequestDto dto) {
        return ResponseEntity.ok(authService.activateStatus(dto));
    }
    // token olusturma ve verify etmek için deneme amaclı end pointler



}
