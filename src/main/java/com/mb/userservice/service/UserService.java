package com.mb.userservice.service;

import java.util.List;

import com.mb.userservice.constant.UserStatus;
import com.mb.userservice.model.entity.UserEntity;
import com.mb.userservice.model.request.CreateUserRequest;
import com.mb.userservice.model.request.UpdateUserRequest;
import com.mb.userservice.model.response.UserResponse;


public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse createUser(CreateUserRequest request);

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UpdateUserRequest request);

    UserResponse updateUserStatus(Long id, UserStatus status);

    void softDeleteUser(Long id);

    default UserResponse mapToUserResponse(UserEntity user) {
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