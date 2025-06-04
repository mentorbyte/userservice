package com.mb.userservice.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.mb.userservice.exception.ServiceException;
import com.mb.userservice.model.entity.DependentEntity;
import com.mb.userservice.model.entity.UserEntity;
import com.mb.userservice.model.response.DependentObj;
import com.mb.userservice.model.response.DependentResponse;
import com.mb.userservice.repository.UserRepository;

@Component
public class UserServiceHelper {

    @Autowired
    private UserRepository userRepository;


    public UserEntity findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ServiceException("user.record.not.found", HttpStatus.NOT_FOUND));
    }

    public List<DependentObj> getDependents(List<DependentEntity> dependentEntities) {
        List<DependentObj> dependents = dependentEntities.stream().map(entity -> {
            DependentObj dependentObj = buildDependentObj(entity);
            return dependentObj;
        }).collect(Collectors.toList());
        return dependents;
    }

    public DependentObj buildDependentObj(DependentEntity entity) {
        DependentObj dependentObj = DependentObj.builder()
                .dependentId(entity.getDependentId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .dateOfBirth(entity.getDateOfBirth())
                .gender(entity.getGender())
                .relationshipType(entity.getRelationshipType())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
        return dependentObj;
    }

    public DependentResponse buildDependentResponse(DependentEntity entity) {
        List<DependentObj> dependents = new ArrayList<>();
        DependentObj dependentObj = buildDependentObj(entity);
        dependents.add(dependentObj);
        DependentResponse dependentResponse = DependentResponse.builder()
                .userId(entity.getUser().getUserId())
                .dependents(dependents)
                .build();
        return dependentResponse;
    }
}
