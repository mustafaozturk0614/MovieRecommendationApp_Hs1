package com.bilgeadam.controller;

import static  com.bilgeadam.constant.EndPoints.*;

import com.bilgeadam.dto.request.ActivateRequestDto;
import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.request.UpdateEmailAndUsernameRequestDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.entity.Auth;
import com.bilgeadam.service.AuthService;
import com.bilgeadam.utility.JwtTokenManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)// api/v1/auth
public class AuthController {

    private final AuthService authService;
    private final JwtTokenManager jwtTokenManager;

    private final CacheManager cacheManager;

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

    @PutMapping(UPDATE)
    public ResponseEntity<String> updateEmailAndUsername(@RequestBody @Valid UpdateEmailAndUsernameRequestDto dto){
        return ResponseEntity.ok(authService.updateEmailAndUsername(dto));
    }

    @GetMapping("/redis")
    @Cacheable(value = "redisexample")
    public String redisExample(String value){
        try {
            Thread.sleep(2000);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return value.toUpperCase();
    }
    @GetMapping("/redis-delete")
    @CacheEvict(cacheNames = "redisexample",allEntries =true)
    public void redisDelete(){}


    @GetMapping("/redis-delete-2")
    public void redisDelete2(String value){
            cacheManager.getCache("redisexample").evict(value);

    }

    }

