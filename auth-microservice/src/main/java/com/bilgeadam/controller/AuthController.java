package com.bilgeadam.controller;

import static  com.bilgeadam.constant.EndPoints.*;

import com.bilgeadam.dto.request.ActivateRequestDto;
import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.request.UpdateEmailAndUsernameRequestDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.entity.Auth;
import com.bilgeadam.exception.AuthManagerException;
import com.bilgeadam.exception.ErrorType;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)// api/v1/auth
public class AuthController {
    @Autowired
    private AuthService authService;

    private final JwtTokenManager jwtTokenManager;

    private final CacheManager cacheManager;

    @PostConstruct
    public void init() {
        System.out.println("JwtTokenManager: {}" + jwtTokenManager);
        System.out.println("CacheManager: {}" + cacheManager);
        if (jwtTokenManager == null) {
            throw new IllegalStateException("JwtTokenManager is not injected!");
        }
        if (cacheManager == null) {
            throw new IllegalStateException("CacheManager is not injected!");
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
        return jwtTokenManager.createToken(id);
    }

    @GetMapping("/create-token-by-id-and-role")
    private Optional<String> creteToken(Long id, String role) {

        System.out.println(jwtTokenManager);
        return jwtTokenManager.createToken(id, role);
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
    private ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> updateEmailAndUsername(@RequestBody @Valid UpdateEmailAndUsernameRequestDto dto) {
        return ResponseEntity.ok(authService.updateEmailAndUsername(dto));
    }

    @GetMapping("/redis")
    public String redisExample(String value) {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return value.toUpperCase();
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Auth>> findAll() {
        return ResponseEntity.ok(authService.findAll());

    }

    @GetMapping(FIND_BY_ID)

    public ResponseEntity<Auth> findById(@RequestParam Long authId) {
        return ResponseEntity.ok(authService.findById(authId).orElseThrow(() -> new AuthManagerException(ErrorType.USER_NOT_FOUND)));
    }

    @GetMapping("get-current-auth")

    public ResponseEntity<Auth> getCurrentAuth(@RequestParam String token) {
        return ResponseEntity.ok(authService.getCurrentAuth(token));
    }
}
