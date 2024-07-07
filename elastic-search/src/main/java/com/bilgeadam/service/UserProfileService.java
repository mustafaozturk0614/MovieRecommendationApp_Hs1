package com.bilgeadam.service;

import com.bilgeadam.entity.UserProfile;
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
}
