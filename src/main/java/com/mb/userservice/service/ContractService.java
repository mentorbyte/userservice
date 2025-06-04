package com.mb.userservice.service;

import java.util.List;
import java.util.stream.Collectors;

import com.mb.userservice.model.entity.ContractEntity;
import com.mb.userservice.model.request.CreateContractRequest;
import com.mb.userservice.model.request.UpdateContractRequest;
import com.mb.userservice.model.response.ContractObj;
import com.mb.userservice.model.response.ContractResponse;


public interface ContractService {

    ContractObj getContractByContractId(Long contractId);
    ContractResponse getContractByUserId(Long userId);
    ContractResponse createContract(Long userId, CreateContractRequest request);
    ContractResponse updateContract(Long contractId, UpdateContractRequest request);
    String deleteContract(Long id);

    default List<ContractObj> getContracts(List<ContractEntity> contractEntities) {
        List<ContractObj> contractObjList = contractEntities.stream().map(entity -> {
            ContractObj contractObj = buildContractObj(entity);
            return contractObj;
        }).collect(Collectors.toList());

        return contractObjList;
    }
    default ContractObj buildContractObj(ContractEntity entity) {
        ContractObj contractObj = ContractObj.builder()
                .contractId(entity.getContractId())
                .contractNumber(entity.getContractNumber())
                .title(entity.getTitle())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .amount(entity.getAmount())
                .contractType(entity.getContractType())
                .status(entity.getStatus())
                .currencyCode(entity.getCurrencyCode())
                .build();
        return contractObj;
    }

}