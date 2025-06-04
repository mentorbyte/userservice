package com.mb.userservice.model.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.mb.userservice.constant.ContractStatus;
import com.mb.userservice.constant.ContractType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateContractRequest {

    @NotBlank
    private String contractNumber;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Past
    @NotNull
    private LocalDate startDate;

    @Past
    @NotNull
    private LocalDate endDate;

    @NotNull
    private BigDecimal amount;

    @NotNull
    @Pattern(regexp = "^(EMPLOYMENT|SERVICE|INSURANCE|PARTNERSHIP|CONSULTING)$", message = "Contract type must be EMPLOYMENT, SERVICE, INSURANCE, PARTNERSHIP, CONSULTING")
    private ContractType contractType;

    @NotNull
    @Pattern(regexp = "^(DRAFT|ACTIVE|SUSPENDED|EXPIRED|TERMINATED|CANCELLED)$", message = "Contract status must be DRAFT, ACTIVE, SUSPENDED, EXPIRED, TERMINATED, CANCELLED")
    private ContractStatus status;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{3}$", message = "Must be a valid ISO 4217 currency code")
    private String currencyCode;
}