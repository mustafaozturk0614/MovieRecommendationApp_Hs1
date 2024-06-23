package com.bilgeadam.manager;

import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.bilgeadam.constant.EndPoints.SAVE;

@FeignClient(url = "http://localhost:8091/api/v1/user",name = "auth-userprofile" ,dismiss404 = true)
public interface IUserManager {

    @PostMapping(SAVE)
    public ResponseEntity<?> saveUserProfile(@RequestBody UserProfileSaveRequestDto dto);



}
