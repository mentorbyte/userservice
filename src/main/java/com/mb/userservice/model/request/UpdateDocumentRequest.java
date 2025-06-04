package com.mb.userservice.model.request;

import com.mb.userservice.constant.DocumentType;
import com.mb.userservice.constant.EntityType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateDocumentRequest {
    private Long documentId;

    @NotBlank
    private String originalFileName;

    @NotBlank
    private String storedFileName;

    @NotNull
    @Pattern(regexp = "^(USER|CONTRACT)$", message = "Role must be 'USER' or 'CONTRACT'")
    private DocumentType documentType;

    @Pattern(regexp = "^(USER|CONTRACT)$", message = "Role must be 'USER' or 'CONTRACT'")
    private EntityType entityType;

    @NotNull
    private Long entityId;

    @Size(max = 500, message = "description can not be max 500 chars")
    private String description;

    private boolean isPublic;

}