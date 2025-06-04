package com.mb.userservice.model.response;

import com.mb.userservice.constant.AddressType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressObj {
    private Long userId;
    private Long addressId;
    private AddressType addressType;
    private String streetAddressLine1;
    private String streetAddressLine2;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String countryCode;
    private boolean isPrimary;
    private boolean isActive;
}