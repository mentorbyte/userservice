package com.mb.userservice.service;

import java.util.List;
import java.util.stream.Collectors;

import com.mb.userservice.model.entity.DependentEntity;
import com.mb.userservice.model.request.CreateDependentRequest;
import com.mb.userservice.model.request.UpdateDependentRequest;
import com.mb.userservice.model.response.DependentObj;
import com.mb.userservice.model.response.DependentResponse;


public interface DependentService {

    DependentObj getDependentByDependentId(Long id);
    DependentResponse getDependentByUserId(Long id);
    DependentResponse createDependent(Long userId, CreateDependentRequest request);
    DependentResponse updateDependent(Long id, UpdateDependentRequest request);
    String deleteDependent(Long id);

    default List<DependentObj> getDependents(List<DependentEntity> dependentEntities) {
        List<DependentObj> dependents = dependentEntities.stream().map(entity -> {
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
        }).collect(Collectors.toList());
        return dependents;
    }

}