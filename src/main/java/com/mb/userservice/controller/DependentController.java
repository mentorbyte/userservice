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

import com.mb.userservice.model.request.UpdateDependentRequest;
import com.mb.userservice.model.response.DependentObj;
import com.mb.userservice.model.response.DependentResponse;
import com.mb.userservice.service.DependentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/dependants")
@Tag(name = "Dependants API endpoints")
public class DependentController {

    @Autowired
    private DependentService dependentService;


    @Operation(summary = "Get dependent endpoint")
    @GetMapping("/{dependentId}")
    public ResponseEntity<DependentObj> getUserById(@PathVariable Long dependentId) {
        return ResponseEntity.ok(dependentService.getDependentByDependentId(dependentId));
    }

    @Operation(summary = "Update dependent endpoint")
    @PutMapping("/{dependentId}")
    public ResponseEntity<DependentResponse> updateUser(
            @PathVariable Long dependentId, @Valid @RequestBody UpdateDependentRequest request) {
        return ResponseEntity.ok(dependentService.updateDependent(dependentId, request));
    }


    @Operation(summary = "Delete dependent endpoint")
    @DeleteMapping("/{dependentId}")
    public ResponseEntity<String> deleteDependent(@PathVariable Long dependentId) {
        dependentService.deleteDependent(dependentId);
        return ResponseEntity.noContent().build();
    }
}