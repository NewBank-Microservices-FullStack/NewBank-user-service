package com.newbank.userservice.repository;

import com.newbank.userservice.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUsername(String username);
    Optional<UserProfile> findByUserId(Long userId);
    Optional<UserProfile> findByEmail(String email);
    boolean existsByUsername(String username);
}