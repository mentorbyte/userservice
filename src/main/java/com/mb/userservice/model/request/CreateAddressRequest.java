package com.mb.userservice.model.request;

import com.mb.userservice.constant.AddressType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAddressRequest {

    private Long addressId;

    @NotNull
    @Pattern(regexp = "^(HOME|WORK|BILLING|SHIPPING|TEMPORARY)$", message = "Address type must be HOME, WORK, BILLING, SHIPPING, TEMPORARY")
    private AddressType addressType;

    @Size(max = 255)
    private String streetAddressLine1;

    @Size(max = 255)
    private String streetAddressLine2;

    @NotBlank
    @Size(max = 100, message = "city can not be max 100 char")
    private String city;

    @Size(max = 100, message = "stateProvince can not be max 100 char")
    private String stateProvince;

    @NotBlank
    @Size(max = 20, message = "postal code can not be max 20 char")
    private String postalCode;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{3}$", message = "Must be a valid ISO country code")
    private String countryCode;

    @NotNull
    private boolean isPrimary;

    @NotNull
    private boolean isActive;

    @NotNull
    private Long userId;
}