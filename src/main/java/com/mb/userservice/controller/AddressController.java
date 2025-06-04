package com.mb.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mb.userservice.model.request.UpdateAddressRequest;
import com.mb.userservice.model.response.AddressObj;
import com.mb.userservice.model.response.AddressResponse;
import com.mb.userservice.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/addresses")
@Tag(name = "Addresses API endpoints")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @Operation(summary = "Get address endpoint")
    @GetMapping("/{addressId}")
    public ResponseEntity<AddressObj> getUserById(@PathVariable Long addressId) {
        return ResponseEntity.ok(addressService.getAddressByAddressId(addressId));
    }

    @Operation(summary = "Update address endpoint")
    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponse> updateAddress(
            @PathVariable Long addressId, @Valid @RequestBody UpdateAddressRequest request) {
        return ResponseEntity.ok(addressService.updateAddress(addressId, request));
    }


    @Operation(summary = "Delete address endpoint")
    @DeleteMapping("/{dependentId}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.noContent().build();
    }
}