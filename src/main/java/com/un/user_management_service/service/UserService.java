package com.un.user_management_service.service;

import java.util.List;

import com.un.user_management_service.constant.UserStatus;
import com.un.user_management_service.model.request.CreateUserRequest;
import com.un.user_management_service.model.request.UpdateUserRequest;
import com.un.user_management_service.model.response.UserResponse;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse createUser(CreateUserRequest request);

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UpdateUserRequest request);

    UserResponse updateUserStatus(Long id, UserStatus status);

    void softDeleteUser(Long id);
    
}