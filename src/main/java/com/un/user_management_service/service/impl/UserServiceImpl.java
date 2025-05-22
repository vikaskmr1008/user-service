package com.un.user_management_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.un.user_management_service.constant.UserStatus;
import com.un.user_management_service.exception.ServiceException;
import com.un.user_management_service.model.entity.UserEntity;
import com.un.user_management_service.model.request.CreateUserRequest;
import com.un.user_management_service.model.request.UpdateUserRequest;
import com.un.user_management_service.model.response.UserResponse;
import com.un.user_management_service.repository.UserRepository;
import com.un.user_management_service.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        UserEntity user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .dateOfBirth(request.getDateOfBirth())
                .status(UserStatus.ACTIVE)
                .build();
        user = userRepository.save(user);
        return mapToUserResponse(user);
    }

    @Override
    public UserResponse getUserById(Long id) {
        return mapToUserResponse(findUserById(id));
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        UserEntity user = findUserById(id);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setDateOfBirth(request.getDateOfBirth());
        return mapToUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUserStatus(Long id, UserStatus status) {
        UserEntity user = findUserById(id);
        user.setStatus(status);
        return mapToUserResponse(userRepository.save(user));
    }

    @Override
    public void softDeleteUser(Long id) {
        UserEntity user = findUserById(id);
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
    }

    private UserEntity findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ServiceException("user.record.not.found", HttpStatus.NOT_FOUND));
    }

    private UserResponse mapToUserResponse(UserEntity user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .build();
    }
}