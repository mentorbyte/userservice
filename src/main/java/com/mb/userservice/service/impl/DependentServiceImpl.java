package com.mb.userservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.mb.userservice.exception.ServiceException;
import com.mb.userservice.helper.UserServiceHelper;
import com.mb.userservice.model.entity.DependentEntity;
import com.mb.userservice.model.entity.UserEntity;
import com.mb.userservice.model.request.CreateDependentRequest;
import com.mb.userservice.model.request.UpdateDependentRequest;
import com.mb.userservice.model.response.DependentObj;
import com.mb.userservice.model.response.DependentResponse;
import com.mb.userservice.repository.DependentRepository;
import com.mb.userservice.service.DependentService;

@Component
public class DependentServiceImpl implements DependentService {

    @Autowired
    private DependentRepository dependentRepository;

    @Autowired
    private UserServiceHelper userServiceHelper;

    @Override
    public DependentObj getDependentByDependentId(Long id) {
        DependentEntity dependentEntity = findUserById(id);
        return userServiceHelper.buildDependentObj(dependentEntity);
    }

    @Override
    public DependentResponse getDependentByUserId(Long userId) {
        UserEntity userEntity = userServiceHelper.findUserById(userId);
        List<DependentEntity> dependentEntities = userEntity.getDependants();
        List<DependentObj> dependents = userServiceHelper.getDependents(dependentEntities);
        return DependentResponse.builder().userId(userId).dependents(dependents).build();
    }

    @Override
    public DependentResponse createDependent(Long userId, CreateDependentRequest request) {
        DependentEntity dependentEntity = new DependentEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        dependentEntity.setUser(userEntity);
        dependentEntity.setFirstName(request.getFirstName());
        dependentEntity.setLastName(request.getLastName());
        dependentEntity.setGender(request.getGender());
        dependentEntity.setRelationshipType(request.getRelationshipType());
        dependentEntity.setDateOfBirth(request.getDateOfBirth());
        dependentRepository.save(dependentEntity);
        return userServiceHelper.buildDependentResponse(dependentEntity);
    }

    @Override
    public DependentResponse updateDependent(Long id, UpdateDependentRequest request) {
        DependentEntity dependentEntity = findUserById(id);
        dependentEntity.setFirstName(request.getFirstName());
        dependentEntity.setLastName(request.getLastName());
        dependentEntity.setGender(request.getGender());
        dependentEntity.setRelationshipType(request.getRelationshipType());
        dependentEntity.setDateOfBirth(request.getDateOfBirth());
        dependentRepository.save(dependentEntity);
        return userServiceHelper.buildDependentResponse(dependentEntity);
    }


    @Override
    public String deleteDependent(Long id) {
        DependentEntity dependentEntity = findUserById(id);
        dependentRepository.delete(dependentEntity);
        return "Record deleted successfully.";
    }

    private DependentEntity findUserById(Long id) {
        return dependentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("dependent.user.record.not.found", HttpStatus.NOT_FOUND));
    }

}
