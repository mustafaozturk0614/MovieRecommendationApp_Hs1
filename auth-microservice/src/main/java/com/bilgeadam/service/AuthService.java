package com.bilgeadam.service;

import com.bilgeadam.dto.request.*;
import com.bilgeadam.dto.response.RegisterResponseDto;
import com.bilgeadam.entity.Auth;
import com.bilgeadam.entity.enums.EStatus;
import com.bilgeadam.exception.AuthManagerException;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.manager.IUserManager;
import com.bilgeadam.mapper.IAuthMapper;
import com.bilgeadam.repository.AuthRepository;
import com.bilgeadam.utility.CodeGenerator;
import com.bilgeadam.utility.JwtTokenManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final IUserManager userManager;
    private final JwtTokenManager jwtTokenManager;

    @Transactional
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
    @Transactional
    public String activateStatus(ActivateRequestDto dto) {
        Optional<Auth> auth=authRepository.findById(dto.getId());
        if (auth.isEmpty()){
            throw  new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        if (auth.get().getStatus().equals(EStatus.ACTIVE)){
            throw  new AuthManagerException(ErrorType.USER_AlREADY_ACTIVE);
        }
        if(auth.get().getActivationCode().equals(dto.getActivationCode())){
            auth.get().setStatus(EStatus.ACTIVE);
            authRepository.save(auth.get());
            userManager.activateStatus(auth.get().getId());
            return "Aktivasyon Basarılı";
        }else {
            throw  new AuthManagerException(ErrorType.INVALID_ACTIVATION_CODE);
        }

    }

    public String login(LoginRequestDto dto) {
    Auth auth=authRepository.findByUsernameAndPassword(dto.getUsername(),dto.getPassword())
            .orElseThrow(()->new AuthManagerException(ErrorType.USER_NOT_FOUND,"Kullanıcı adı veya şifre Hatalı "));

    if (!auth.getStatus().equals(EStatus.ACTIVE)){
        throw  new AuthManagerException(ErrorType.ACCOUNT_NOT_ACTIVE);
    }
        return jwtTokenManager.createToken(auth.getId(),auth.getRole().toString())
                .orElseThrow(()->new AuthManagerException(ErrorType.TOKEN_NOT_CREATED));
    }

    public String updateEmailAndUsername(UpdateEmailAndUsernameRequestDto dto) {
        Auth auth=authRepository.findById(dto.getId()).orElseThrow(()->new AuthManagerException(ErrorType.USER_NOT_FOUND));
        auth.setUsername(dto.getUsername());
        auth.setEmail(dto.getEmail());
        authRepository.save(auth);
        return "Bilgileriniz Güncellendi";
    }


}
