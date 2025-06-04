package com.mb.userservice.model.request;


import com.mb.userservice.constant.UserStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StatusUpdateRequest {

    @NotNull
    @Pattern(regexp = "^(ACTIVE|INACTIVE|SUSPENDED|PENDING_VERIFICATION)$", message = "Role must be ACTIVE, INACTIVE, SUSPENDED, or PENDING_VERIFICATION")
    private UserStatus status;
    
}