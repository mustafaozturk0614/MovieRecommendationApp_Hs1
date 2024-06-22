package com.bilgeadam.controller;

import static  com.bilgeadam.constant.EndPoints.*;

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
}