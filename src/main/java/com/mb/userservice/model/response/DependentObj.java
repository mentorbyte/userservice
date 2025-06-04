package com.mb.userservice.model.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.mb.userservice.constant.Gender;
import com.mb.userservice.constant.RelationshipType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DependentObj {
    private Long dependentId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private RelationshipType relationshipType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}