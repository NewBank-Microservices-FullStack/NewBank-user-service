package com.newbank.userservice.controller;

import com.newbank.userservice.dto.UserProfileResponse;
import com.newbank.userservice.entity.UserProfile;
import com.newbank.userservice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserProfileController {
    private final UserProfileService userProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponse> getUser(@PathVariable Long id) {
        log.info("GET /api/users/{}", id);
        return ResponseEntity.ok(userProfileService.getUserById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserProfileResponse> getByUsername(@PathVariable String username) {
        log.info("GET /api/users/username/{}", username);
        return ResponseEntity.ok(userProfileService.getByUsername(username));
    }

    @GetMapping("/user-id/{userId}")
    public ResponseEntity<UserProfileResponse> getByUserId(@PathVariable Long userId) {
        log.info("GET /api/users/user-id/{}", userId);
        return ResponseEntity.ok(userProfileService.getByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<UserProfileResponse> createUserProfile(@RequestBody UserProfile userProfile) {
        log.info("POST /api/users - Creating profile for {}", userProfile.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userProfileService.createUserProfile(userProfile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileResponse> updateUserProfile(
            @PathVariable Long id,
            @RequestBody UserProfile updates) {
        log.info("PUT /api/users/{}", id);
        return ResponseEntity.ok(userProfileService.updateUserProfile(id, updates));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("User Service is running");
    }
}