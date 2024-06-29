package com.bilgeadam.manager;

import com.bilgeadam.dto.request.UpdateEmailAndUsernameRequestDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.bilgeadam.constant.EndPoints.UPDATE;

@FeignClient(url = "http://localhost:8090/api/v1/auth", name = "user-auth")
public interface AuthManager {

    @PutMapping(UPDATE)
     ResponseEntity<String> updateEmailAndUsername(@RequestBody @Valid UpdateEmailAndUsernameRequestDto dto);

}
