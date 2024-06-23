package com.bilgeadam.repository;

import com.bilgeadam.entity.UserProfile;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {

    Optional<UserProfile> findByAuthId(Long authId);
}
