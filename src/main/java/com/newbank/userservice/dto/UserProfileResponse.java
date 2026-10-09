package com.newbank.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponse {
    private Long id;
    private Long userId;
    private String username;
    private String email;
    private String fullname;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String postalCode;
}