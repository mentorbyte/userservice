package com.mb.userservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mb.userservice.constant.UserStatus;
import com.mb.userservice.helper.UserServiceHelper;
import com.mb.userservice.model.entity.UserEntity;
import com.mb.userservice.model.request.CreateUserRequest;
import com.mb.userservice.model.request.UpdateUserRequest;
import com.mb.userservice.model.response.UserResponse;
import com.mb.userservice.repository.UserRepository;
import com.mb.userservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceHelper userServiceHelper;

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
        return mapToUserResponse(userServiceHelper.findUserById(id));
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        UserEntity user = userServiceHelper.findUserById(id);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setDateOfBirth(request.getDateOfBirth());
        return mapToUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUserStatus(Long id, UserStatus status) {
        UserEntity user = userServiceHelper.findUserById(id);
        user.setStatus(status);
        return mapToUserResponse(userRepository.save(user));
    }

    @Override
    public void softDeleteUser(Long id) {
        UserEntity user = userServiceHelper.findUserById(id);
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
    }
}