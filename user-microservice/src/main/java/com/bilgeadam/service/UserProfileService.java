package com.bilgeadam.service;

import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.entity.UserProfile;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public Object saveUserProfile(UserProfileSaveRequestDto dto) {
        UserProfile userProfile= IUserMapper.INSTANCE.toUserProfile(dto);
        return userProfileRepository.save(userProfile);
    }
}
