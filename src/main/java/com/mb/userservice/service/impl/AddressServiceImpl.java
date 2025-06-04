package com.mb.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.mb.userservice.exception.ServiceException;
import com.mb.userservice.helper.UserServiceHelper;
import com.mb.userservice.model.entity.AddressEntity;
import com.mb.userservice.model.entity.UserEntity;
import com.mb.userservice.model.request.CreateAddressRequest;
import com.mb.userservice.model.request.UpdateAddressRequest;
import com.mb.userservice.model.response.AddressObj;
import com.mb.userservice.model.response.AddressResponse;
import com.mb.userservice.repository.AddressRepository;
import com.mb.userservice.service.AddressService;

@Component
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserServiceHelper userServiceHelper;


    @Override
    public AddressObj getAddressByAddressId(Long addressId) {
        AddressEntity addressEntity = findById(addressId);
        return buildAddress(addressEntity);
    }

    @Override
    public AddressResponse getAddressByUserId(Long userId) {
        UserEntity userEntity = userServiceHelper.findUserById(userId);
        List<AddressEntity> addressEntities = userEntity.getAddress();
        List<AddressObj> addressObjs = getAddresses(addressEntities);
        return AddressResponse.builder().userId(userId).addresses(addressObjs).build();
    }

    @Override
    public AddressResponse createAddress(Long addressId, CreateAddressRequest request) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(request.getCity());
        /*
         * To DO
         * Add more info to address entity
         */
        addressRepository.save(addressEntity);
        return buildAddressResponse(addressEntity);
    }
    public AddressResponse buildAddressResponse(AddressEntity entity) {
        List<AddressObj> addresses = new ArrayList<>();
        AddressObj contractObj = buildAddress(entity);
        addresses.add(contractObj);
        AddressResponse addressResponse = AddressResponse.builder()
                .userId(entity.getUser().getUserId())
                .addresses(addresses)
                .build();
        return addressResponse;
    }


    @Override
    public AddressResponse updateAddress(Long addressId, UpdateAddressRequest request) {
        AddressEntity addressEntity = findById(addressId);
        addressEntity.setCity(request.getCity());
         /*
         * To DO
         * Add more info to address entity
         */
        addressRepository.save(addressEntity);
        return buildAddressResponse(addressEntity);
    }

    @Override
    public String deleteAddress(Long addressId) {
        AddressEntity addressEntity = findById(addressId);
        addressRepository.delete(addressEntity);
        return "Record deleted successfully.";
    }

        private AddressEntity findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new ServiceException("address.record.not.found", HttpStatus.NOT_FOUND));
    }

}
