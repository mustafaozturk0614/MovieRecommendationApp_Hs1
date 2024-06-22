package com.bilgeadam.service;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.entity.Auth;
import com.bilgeadam.exception.AuthManagerException;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.mapper.IAuthMapper;
import com.bilgeadam.repository.AuthRepository;
import com.bilgeadam.utility.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public RegisterResponseDto register(RegisterRequestDto dto) {

        Auth auth= IAuthMapper.INSTANCE.toAuth(dto);

       if (authRepository.existsByUsername(dto.getUsername())){
           throw new AuthManagerException(ErrorType.USERNAME_ALREADY_EXISTS);
       }

        String activationCode= CodeGenerator.generateCode();
        auth.setActivationCode(activationCode);
        authRepository.save(auth);

        RegisterResponseDto registerResponseDto=IAuthMapper.INSTANCE.toRegisterResponseDto(auth);

        return  registerResponseDto;//

    }
}
