package com.mb.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mb.userservice.model.request.UpdateContractRequest;
import com.mb.userservice.model.response.ContractObj;
import com.mb.userservice.model.response.ContractResponse;
import com.mb.userservice.model.response.DocumentResponse;
import com.mb.userservice.service.ContractService;
import com.mb.userservice.service.DocumentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/contracts")
@Tag(name = "Contract API endpoints")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private DocumentService documentService;


    @Operation(summary = "Get contract endpoint")
    @GetMapping("/{contractId}")
    public ResponseEntity<ContractObj> getUserById(@PathVariable Long contractId) {
        return ResponseEntity.ok(contractService.getContractByContractId(contractId));
    }

    @Operation(summary = "Update contract endpoint")
    @PutMapping("/{contractId}")
    public ResponseEntity<ContractResponse> updateContract(
            @PathVariable Long contractId, @Valid @RequestBody UpdateContractRequest request) {
        return ResponseEntity.ok(contractService.updateContract(contractId, request));
    }


    @Operation(summary = "Delete contract endpoint")
    @DeleteMapping("/{contractId}")
    public ResponseEntity<String> deleteContract(@PathVariable Long contractId) {
        contractService.deleteContract(contractId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get contract endpoint")
    @GetMapping("/{contractId}")
    public ResponseEntity<DocumentResponse> getDocumentByContractId(@PathVariable Long contractId) {
        return ResponseEntity.ok(documentService.getDocumentByEntityId(contractId));
    }
}