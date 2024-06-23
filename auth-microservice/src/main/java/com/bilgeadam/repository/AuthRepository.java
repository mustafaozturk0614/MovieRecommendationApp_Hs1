package com.bilgeadam.repository;

import com.bilgeadam.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth,Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
