package com.bilgeadam.repository;

import com.bilgeadam.entity.Auth;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AuthRepository extends JpaRepository<Auth,Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Auth>  findByUsernameAndPassword(String username, String password);
}
