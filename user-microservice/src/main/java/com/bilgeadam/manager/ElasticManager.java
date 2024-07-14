package com.bilgeadam.manager;

import com.bilgeadam.dto.request.UpdateEmailAndUsernameRequestDto;
import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.entity.UserProfile;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.bilgeadam.constant.EndPoints.SAVE;
import static com.bilgeadam.constant.EndPoints.UPDATE;

@FeignClient(url = "${feign.elasticsearch}", name = "user-elastic")
public interface ElasticManager {

    @PostMapping(SAVE)
     ResponseEntity<UserProfile> save(@RequestBody  UserProfileSaveRequestDto dto);

    
}
