package com.bilgeadam.repository;

import com.bilgeadam.entity.UserProfile;
import org.apache.catalina.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


import java.util.Optional;

public interface UserProfileRepository extends ElasticsearchRepository<UserProfile, String> {

    Optional<UserProfile> findByAuthId(Long authId);

    Optional<UserProfile> findByUsername(String username);
}
