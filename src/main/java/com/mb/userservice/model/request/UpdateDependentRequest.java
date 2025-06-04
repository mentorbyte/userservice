package com.mb.userservice.model.request;

import java.time.LocalDate;

import com.mb.userservice.constant.Gender;
import com.mb.userservice.constant.RelationshipType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateDependentRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;
    
    private String phoneNumber;

    @Past
    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    @Pattern(regexp = "^(MALE|FEMALE|OTHER|PREFER_NOT_TO_SAY)$", message = "Gender must be MALE, FEMALE, OTHER, PREFER_NOT_TO_SAY")
    private Gender gender;

    @NotNull
    @Pattern(regexp = "^(SPOUSE|CHILD|PARENT|SIBLING|GUARDIAN|OTHER)$", message = "Relationship type must be SPOUSE, CHILD, PARENT, SIBLING, GUARDIAN, OTHER")
    private RelationshipType relationshipType;
}