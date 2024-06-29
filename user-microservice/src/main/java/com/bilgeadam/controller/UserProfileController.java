package com.bilgeadam.controller;

import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.dto.request.UserProfileUpdateRequestDto;
import com.bilgeadam.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static   com.bilgeadam.constant.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(SAVE)
    public ResponseEntity<?> saveUserProfile(@RequestBody UserProfileSaveRequestDto dto){

        return ResponseEntity.ok(userProfileService.saveUserProfile(dto));

    }

    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<String> activateStatus(@RequestParam Long authId){
        return ResponseEntity.ok(userProfileService.activateStatus(authId));
    }

    // updateUserProfile  eğer username veya email değişmiş ise auth microservisindede update edilecek
    @PutMapping(UPDATE)
    public ResponseEntity<String> updateUserProfile(@RequestBody @Valid UserProfileUpdateRequestDto dto){

        return ResponseEntity.ok(userProfileService.updateUserProfile(dto));


    }

}
