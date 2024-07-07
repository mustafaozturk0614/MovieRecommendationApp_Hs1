package com.bilgeadam.manager;

import com.bilgeadam.dto.response.UserProfileFindAllResponseDto;
import com.bilgeadam.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.bilgeadam.constant.EndPoints.FIND_ALL;

@FeignClient(url = "http://localhost:8091/api/v1/user",name = "elastic-user",dismiss404 = true)
public interface IUserManager {

    @GetMapping(FIND_ALL)
     ResponseEntity<List<UserProfileFindAllResponseDto>> findAll();
}
