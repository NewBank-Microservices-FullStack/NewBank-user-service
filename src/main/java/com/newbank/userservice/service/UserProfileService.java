package com.newbank.userservice.service;

import com.newbank.userservice.entity.UserProfile;
import com.newbank.userservice.dto.UserProfileResponse;
import com.newbank.userservice.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    public UserProfileResponse getUserById(Long id) {
        log.info("Fetching user profile with id: {}", id);
        return userProfileRepository.findById(id)
                .map(this::convertToResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserProfileResponse getByUsername(String username) {
        log.info("Fetching user profile with username: {}", username);
        return userProfileRepository.findByUsername(username)
                .map(this::convertToResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserProfileResponse getByUserId(Long userId) {
        log.info("Fetching user profile with userId: {}", userId);
        return userProfileRepository.findByUserId(userId)
                .map(this::convertToResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserProfileResponse createUserProfile(UserProfile userProfile) {
        log.info("Creating user profile for username: {}", userProfile.getUsername());
        UserProfile saved = userProfileRepository.save(userProfile);
        return convertToResponse(saved);
    }

    public UserProfileResponse updateUserProfile(Long id, UserProfile updates) {
        log.info("Updating user profile with id: {}", id);
        UserProfile existing = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (updates.getPhoneNumber() != null) {
            existing.setPhoneNumber(updates.getPhoneNumber());
        }
        if (updates.getAddress() != null) {
            existing.setAddress(updates.getAddress());
        }
        if (updates.getCity() != null) {
            existing.setCity(updates.getCity());
        }

        UserProfile updated = userProfileRepository.save(existing);
        return convertToResponse(updated);
    }

    private UserProfileResponse convertToResponse(UserProfile profile) {
        return UserProfileResponse.builder()
                .id(profile.getId())
                .userId(profile.getUserId())
                .username(profile.getUsername())
                .email(profile.getEmail())
                .fullname(profile.getFullname())
                .phoneNumber(profile.getPhoneNumber())
                .address(profile.getAddress())
                .city(profile.getCity())
                .state(profile.getState())
                .postalCode(profile.getPostalCode())
                .build();
    }
}