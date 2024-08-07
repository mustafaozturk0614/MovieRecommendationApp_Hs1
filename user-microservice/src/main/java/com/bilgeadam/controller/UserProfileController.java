package com.bilgeadam.controller;

import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.dto.request.UserProfileUpdateRequestDto;
import com.bilgeadam.dto.response.UserProfileFindAllResponseDto;
import com.bilgeadam.entity.UserProfile;
import com.bilgeadam.repository.UserProfileRepository;
import com.bilgeadam.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static   com.bilgeadam.constant.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(SAVE)
    public ResponseEntity<?> saveUserProfile(@RequestHeader("Authorization") String token,@RequestBody UserProfileSaveRequestDto dto){

        return ResponseEntity.ok(userProfileService.saveUserProfile(dto));

    }

    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<String> activateStatus(@RequestHeader("Authorization") String token,@RequestParam Long authId){
        return ResponseEntity.ok(userProfileService.activateStatus(authId));
    }

    // updateUserProfile  eğer username veya email değişmiş ise auth microservisindede update edilecek
    @PutMapping(UPDATE)
    public ResponseEntity<String> updateUserProfile(@RequestBody @Valid UserProfileUpdateRequestDto dto){

        return ResponseEntity.ok(userProfileService.updateUserProfile(dto));


    }

    //findbyusername endpointi yazalım cachelemi isleminide servis katmanıdna yapalım
    @GetMapping(FIND_BY_USERNAME+"/{username}")
    public ResponseEntity<UserProfile> findbyUsername(@PathVariable String username){
        return ResponseEntity.ok(userProfileService.findByUsername(username));
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<UserProfileFindAllResponseDto>> findAll(){
        return ResponseEntity.ok(userProfileService.findAll());
    }
}
