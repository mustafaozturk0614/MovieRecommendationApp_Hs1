package com.bilgeadam.service;

import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.entity.UserProfile;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private  final UserProfileRepository userProfileRepository;
    public Iterable<UserProfile> findAll() {
        System.out.println( userProfileRepository.findByUsername("musty"));
        return  userProfileRepository.findAll();
    }

    public UserProfile save(UserProfileSaveRequestDto dto) {
        UserProfile userProfile = IUserMapper.INSTANCE.toUserProfile(dto);
        userProfileRepository.save(userProfile);
        return userProfile;
    }
}
