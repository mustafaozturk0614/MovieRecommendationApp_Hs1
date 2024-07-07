package com.bilgeadam.controller;

import com.bilgeadam.entity.UserProfile;
import com.bilgeadam.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

import static   com.bilgeadam.constant.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserProfileController {

    private final UserProfileService userProfileService;


    @GetMapping(FIND_ALL)
    public ResponseEntity<Iterable<UserProfile>> findAll(){
        return ResponseEntity.ok(userProfileService.findAll());
    }
}
