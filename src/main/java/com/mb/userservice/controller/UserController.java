package com.mb.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mb.userservice.model.request.CreateAddressRequest;
import com.mb.userservice.model.request.CreateContractRequest;
import com.mb.userservice.model.request.CreateDependentRequest;
import com.mb.userservice.model.request.CreateUserRequest;
import com.mb.userservice.model.request.StatusUpdateRequest;
import com.mb.userservice.model.request.UpdateUserRequest;
import com.mb.userservice.model.response.AddressResponse;
import com.mb.userservice.model.response.ContractResponse;
import com.mb.userservice.model.response.DependentResponse;
import com.mb.userservice.model.response.DocumentResponse;
import com.mb.userservice.model.response.UserResponse;
import com.mb.userservice.service.AddressService;
import com.mb.userservice.service.ContractService;
import com.mb.userservice.service.DependentService;
import com.mb.userservice.service.DocumentService;
import com.mb.userservice.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User API endpoints")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DependentService dependentService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private AddressService addressService;

    @Operation(summary = "Get all user endpoint")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Create user endpoint")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @Operation(summary = "Get user by id endpoint")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Operation(summary = "Update user endpoint")
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long userId, @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    @Operation(summary = "Update user status endpoint")
    @PatchMapping("/{userId}/status")
    public ResponseEntity<UserResponse> updateUserStatus(
            @PathVariable Long userId,
            @RequestBody StatusUpdateRequest request) {
        return ResponseEntity.ok(userService.updateUserStatus(userId, request.getStatus()));
    }

    @Operation(summary = "Delete user endpoint")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> softDeleteUser(@PathVariable Long userId) {
        userService.softDeleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get dependent endpoint")
    @GetMapping("/{userId}/dependants")
    public ResponseEntity<DependentResponse> getDependant(@PathVariable Long userId) {
        return ResponseEntity.ok(dependentService.getDependentByUserId(userId));
    }

    @Operation(summary = "Create dependent endpoint")
    @PostMapping("/{userId}/dependants")
    public ResponseEntity<DependentResponse> createDependant(@PathVariable Long userId,
            @Valid @RequestBody CreateDependentRequest request) {
        return ResponseEntity.ok(dependentService.createDependent(userId, request));
    }

    @Operation(summary = "Get contract endpoint")
    @GetMapping("/{userId}/contracts")
    public ResponseEntity<ContractResponse> getContract(@PathVariable Long userId) {
        return ResponseEntity.ok(contractService.getContractByUserId(userId));
    }

    @Operation(summary = "Create contract endpoint")
    @PostMapping("/{userId}/contracts")
    public ResponseEntity<ContractResponse> createContract(@PathVariable Long userId,
            @Valid @RequestBody CreateContractRequest request) {
        return ResponseEntity.ok(contractService.createContract(userId, request));
    }

    @Operation(summary = "Get address endpoint")
    @GetMapping("/{userId}/addresses")
    public ResponseEntity<AddressResponse> getAddress(@PathVariable Long userId) {
        return ResponseEntity.ok(addressService.getAddressByUserId(userId));
    }

    @Operation(summary = "Create address endpoint")
    @PostMapping("/{userId}/addresses")
    public ResponseEntity<AddressResponse> createAddress(@PathVariable Long userId,
            @Valid @RequestBody CreateAddressRequest request) {
        return ResponseEntity.ok(addressService.createAddress(userId, request));
    }

    @Operation(summary = "Get document endpoint")
    @GetMapping("/{userId}/documents")
    public ResponseEntity<DocumentResponse> getDocument(@PathVariable Long userId) {
        return ResponseEntity.ok(documentService.getDocumentByEntityId(userId));
    }
}