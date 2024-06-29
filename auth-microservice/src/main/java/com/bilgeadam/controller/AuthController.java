package com.bilgeadam.controller;

import static  com.bilgeadam.constant.EndPoints.*;

import com.bilgeadam.dto.request.ActivateRequestDto;
import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.entity.Auth;
import com.bilgeadam.service.AuthService;
import com.bilgeadam.utility.JwtTokenManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)// api/v1/auth
public class AuthController {

    private final AuthService authService;
    private final JwtTokenManager jwtTokenManager;

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

    @GetMapping("/create-token-by-id")
    private Optional<String> creteToken(Long id) {
        return jwtTokenManager.createToken(id);
    }

    @GetMapping("/create-token-by-id-and-role")
    private Optional<String> creteToken(Long id,String role) {
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
}
