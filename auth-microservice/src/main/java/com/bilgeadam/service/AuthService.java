package com.bilgeadam.service;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.entity.Auth;
import com.bilgeadam.exception.AuthManagerException;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.manager.IUserManager;
import com.bilgeadam.mapper.IAuthMapper;
import com.bilgeadam.repository.AuthRepository;
import com.bilgeadam.utility.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final IUserManager userManager;

    public RegisterResponseDto register(RegisterRequestDto dto) {

        Auth auth= IAuthMapper.INSTANCE.toAuth(dto);

        String activationCode= CodeGenerator.generateCode();
        auth.setActivationCode(activationCode);
        authRepository.save(auth);
        // feing client ile haberleşme
        UserProfileSaveRequestDto userProfileSaveRequestDto=IAuthMapper.INSTANCE.toUserProfileSaveRequestDto(auth);
        userManager.saveUserProfile(userProfileSaveRequestDto);

        RegisterResponseDto registerResponseDto=IAuthMapper.INSTANCE.toRegisterResponseDto(auth);

        return  registerResponseDto;//

    }
}
