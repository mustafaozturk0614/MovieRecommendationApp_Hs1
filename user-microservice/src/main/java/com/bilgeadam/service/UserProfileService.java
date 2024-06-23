package com.bilgeadam.service;

import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.entity.UserProfile;
import com.bilgeadam.entity.enums.EStatus;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.CipherSpi;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public Object saveUserProfile(UserProfileSaveRequestDto dto) {
        UserProfile userProfile= IUserMapper.INSTANCE.toUserProfile(dto);
        return userProfileRepository.save(userProfile);
    }

    public String activateStatus(Long authId) {
        Optional<UserProfile> userProfile= userProfileRepository.findByAuthId(authId);
        if (userProfile.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        userProfile.get().setStatus(EStatus.ACTIVE);
        userProfileRepository.save(userProfile.get());
        return "Aktivasyon Basarılı";
    }
}
