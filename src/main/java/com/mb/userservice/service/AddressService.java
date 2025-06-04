package com.mb.userservice.service;

import java.util.List;
import java.util.stream.Collectors;

import com.mb.userservice.model.entity.AddressEntity;
import com.mb.userservice.model.request.CreateAddressRequest;
import com.mb.userservice.model.request.UpdateAddressRequest;
import com.mb.userservice.model.response.AddressObj;
import com.mb.userservice.model.response.AddressResponse;

public interface AddressService {

    AddressObj getAddressByAddressId(Long addressId);

    AddressResponse getAddressByUserId(Long userId);

    AddressResponse createAddress(Long addressId, CreateAddressRequest request);

    AddressResponse updateAddress(Long addressId, UpdateAddressRequest request);

    String deleteAddress(Long addressId);

    default AddressObj buildAddress(AddressEntity entity) {
        /*
         * TO DO add more info to address object
         */
        return AddressObj.builder().addressId(entity.getAddressId()).build();
    }

    default List<AddressObj> getAddresses(List<AddressEntity> addressEntities) {
        List<AddressObj> addressObjs = addressEntities.stream().map(entity -> {
            AddressObj addressObj = AddressObj.builder()
                    /*
                     * To. DO Add more info to addressObj
                     */

                    .build();
            return addressObj;
        }).collect(Collectors.toList());
        return addressObjs;
    }

}