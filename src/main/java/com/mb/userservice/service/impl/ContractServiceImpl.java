package com.mb.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.mb.userservice.exception.ServiceException;
import com.mb.userservice.helper.UserServiceHelper;
import com.mb.userservice.model.entity.ContractEntity;
import com.mb.userservice.model.entity.UserEntity;
import com.mb.userservice.model.request.CreateContractRequest;
import com.mb.userservice.model.request.UpdateContractRequest;
import com.mb.userservice.model.response.ContractObj;
import com.mb.userservice.model.response.ContractResponse;
import com.mb.userservice.repository.ContractRepository;
import com.mb.userservice.service.ContractService;

@Component
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private UserServiceHelper userServiceHelper;

    @Override
    public ContractObj getContractByContractId(Long contractId) {
         ContractEntity contractEntity = findUserById(contractId);
        return buildContractObj(contractEntity);
    }

    @Override
    public ContractResponse getContractByUserId(Long userId) {
        UserEntity userEntity = userServiceHelper.findUserById(userId);
        List<ContractEntity> contractEntities = userEntity.getContracts();
        List<ContractObj> contractObjs = getContracts(contractEntities);
        return ContractResponse.builder().userId(userId).contracts(contractObjs).build();
    }

    public List<ContractObj> getContracts(List<ContractEntity> contractEntities) {
        List<ContractObj> contracts = contractEntities.stream().map(entity -> {
            ContractObj contractObj = buildContractObj(entity);
            return contractObj;
        }).collect(Collectors.toList());
        return contracts;
    }

    @Override
    public ContractResponse createContract(Long userId, CreateContractRequest request) {
        ContractEntity contractEntity = new ContractEntity();
        contractEntity.setContractNumber(request.getContractNumber());
        contractEntity.setContractType(request.getContractType());
        contractEntity.setTitle(request.getTitle());
        contractEntity.setDescription(request.getDescription());
        contractEntity.setStartDate(request.getStartDate());
        contractEntity.setEndDate(request.getEndDate());
        contractEntity.setStatus(request.getStatus());
        contractEntity.setAmount(request.getAmount());
        contractEntity.setCurrencyCode(request.getCurrencyCode());
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        contractEntity.setUser(userEntity);
        
        contractRepository.save(contractEntity);
        return buildContractResponse(contractEntity);
    }

    public ContractResponse buildContractResponse(ContractEntity entity) {
        List<ContractObj> contracts = new ArrayList<>();
        ContractObj contractObj = buildContractObj(entity);
        contracts.add(contractObj);
        ContractResponse contractResponse = ContractResponse.builder()
                .userId(entity.getUser().getUserId())
                .contracts(contracts)
                .build();
        return contractResponse;
    }

    @Override
    public ContractResponse updateContract(Long contractId, UpdateContractRequest request) {
        ContractEntity contractEntity = findUserById(contractId);
        contractEntity.setContractNumber(request.getContractNumber());
        contractEntity.setContractType(request.getType());
        contractEntity.setTitle(request.getTitle());
        contractEntity.setDescription(request.getDescription());
        contractEntity.setStartDate(request.getStartDate());
        contractEntity.setEndDate(request.getEndDate());
        contractEntity.setStatus(request.getStatus());
        contractEntity.setAmount(request.getAmount());
        contractEntity.setCurrencyCode(request.getCurrencyCode());
        contractRepository.save(contractEntity);
        return buildContractResponse(contractEntity);
    }

    @Override
    public String deleteContract(Long contractId) {
        ContractEntity contractEntity = findUserById(contractId);
        contractRepository.delete(contractEntity);
        return "Record deleted successfully.";
    }


    private ContractEntity findUserById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ServiceException("contract.user.record.not.found", HttpStatus.NOT_FOUND));
    }

}
