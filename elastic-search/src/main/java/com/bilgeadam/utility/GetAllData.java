package com.bilgeadam.utility;

import com.bilgeadam.dto.response.UserProfileFindAllResponseDto;
import com.bilgeadam.entity.UserProfile;
import com.bilgeadam.manager.IUserManager;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.UserProfileRepository;
import com.bilgeadam.service.UserProfileService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllData {

    private final UserProfileRepository userProfileRepository;

    private final IUserManager userManager;
    private final UserProfileService userProfileService;

    @PostConstruct
    public void initData(){
        if (userProfileRepository.count()<=0){
            List<UserProfileFindAllResponseDto> userList = userManager.findAll().getBody();
            System.out.println("userlist = " + userList);
            userList.forEach(x-> {
            UserProfile userProfile = UserProfile.builder()
                    .name(x.getName())
                    .about(x.getAbout())
                    .id(x.getId())
                    .authId(x.getAuthId())
                    .email(x.getEmail())
                    .avatar(x.getAvatar())
                    .address(x.getAddress())
                    .username(x.getUsername())
                    .birthDate(x.getBirthDate() == null ? null : x.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli())
                    .status(x.getStatus())
                    .build();

            userProfileRepository.save(userProfile);
            });


        }else{
            System.out.println("daha once veri eklendi");
       //    userProfileRepository.deleteAll();
        }

    }
//    @Bean
//    @Transactional
//    CommandLineRunner initTestData(UserProfileService userProfileService) {
//        return args -> {
//
//            if (userProfileRepository.count() <= 0) {
//                List<UserProfile> userList = userManager.findAll().getBody();
//                userProfileRepository.saveAll(userList);
//            }
//        };
//    }
}
