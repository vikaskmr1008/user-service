package com.un.user_management_service.model.response;

import java.time.LocalDate;

import com.un.user_management_service.constant.UserStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private UserStatus status;
    
}