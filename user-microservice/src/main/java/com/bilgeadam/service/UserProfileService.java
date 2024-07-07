package com.bilgeadam.service;

import com.bilgeadam.dto.request.UpdateEmailAndUsernameRequestDto;
import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.dto.request.UserProfileUpdateRequestDto;
import com.bilgeadam.dto.response.UserProfileFindAllResponseDto;
import com.bilgeadam.entity.UserProfile;
import com.bilgeadam.entity.enums.EStatus;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.manager.AuthManager;
import com.bilgeadam.manager.ElasticManager;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.UserProfileRepository;
import com.bilgeadam.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.CipherSpi;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

import static com.bilgeadam.constant.EndPoints.UPDATE;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final JwtTokenManager jwtTokenManager;

    private final AuthManager authManager;
    private final ElasticManager elasticManager;
    private final CacheManager cacheManager;


    public UserProfile saveUserProfile(UserProfileSaveRequestDto dto) {
        UserProfile userProfile= IUserMapper.INSTANCE.toUserProfile(dto);
       userProfileRepository.save(userProfile);
       dto.setId(userProfile.getId());

        /// elastic searche veri ileteceğiz
        elasticManager.save(dto);

        return userProfile;
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

    public String updateUserProfile(UserProfileUpdateRequestDto dto) {

        Long authId=jwtTokenManager.getAuthIdFromToken(dto.getToken())
                .orElseThrow(()->new UserManagerException(ErrorType.INVALID_TOKEN));

        UserProfile userProfile=userProfileRepository.findByAuthId(authId)
                .orElseThrow(()->new UserManagerException(ErrorType.USER_NOT_FOUND));
        if (!dto.getUsername().equals(
                userProfile.getUsername()) ||
       ! dto.getEmail().equals(userProfile.getEmail())
        ){
            userProfile.setUsername(dto.getUsername());
            userProfile.setEmail(dto.getEmail());
            // authservice a istek atacagız
            authManager.updateEmailAndUsername(UpdateEmailAndUsernameRequestDto.builder()
                            .email(dto.getEmail())
                            .username(dto.getUsername())
                            .id(authId)
                    .build());
        }

        userProfile.setAbout(dto.getAbout());
        userProfile.setAddress(dto.getAddress());
        userProfile.setBirthDate(dto.getBirthDate());
        userProfile.setPhone(dto.getPhone());
        userProfile.setSurName(dto.getSurName());

        userProfileRepository.save(userProfile);
     //   cacheManager.getCache("findByUsername").evict(userProfile.getUsername());
        cacheManager.getCache("findByUsername").put(userProfile.getUsername(),userProfile);
        return "Bilgileriniz Güncellendi";
    }

    @Cacheable(value = "findByUsername")
    public UserProfile findByUsername(String username) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return   userProfileRepository.findByUsername(username)
              .orElseThrow(()->new UserManagerException(ErrorType.USER_NOT_FOUND));
    }

    public List<UserProfileFindAllResponseDto> findAll() {
        return IUserMapper.INSTANCE.toUserProfileFindAllResponseDto( userProfileRepository.findAll());
    }
}
