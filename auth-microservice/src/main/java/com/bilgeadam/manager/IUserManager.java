package com.bilgeadam.manager;

import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static com.bilgeadam.constant.EndPoints.ACTIVATE_STATUS;
import static com.bilgeadam.constant.EndPoints.SAVE;

@FeignClient(url = "http://localhost:8091/api/v1/user",name = "auth-userprofile" ,dismiss404 = true)
public interface IUserManager {

    @PostMapping(SAVE)
     ResponseEntity<?> saveUserProfile(@RequestBody UserProfileSaveRequestDto dto);

    @PostMapping(ACTIVATE_STATUS)
     ResponseEntity<String> activateStatus(@RequestParam Long authId);

}
