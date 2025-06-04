package com.mb.userservice.model.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.mb.userservice.constant.ContractStatus;
import com.mb.userservice.constant.ContractType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContractObj {
    private Long contractId;
    private String contractNumber;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal amount;
    private ContractType contractType;
    private ContractStatus status;
    private String currencyCode;
}