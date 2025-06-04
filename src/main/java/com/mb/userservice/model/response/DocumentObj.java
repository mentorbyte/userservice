package com.mb.userservice.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mb.userservice.constant.DocumentType;
import com.mb.userservice.constant.EntityType;

import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class DocumentObj {
    private Long documentId;
    private String originalFileName;
    private String storedFileName;
    private String fileType;
    private Long fileSize;
    private DocumentType documentType;
    private EntityType entityType;
    private Long entityId;
    private String description;
    private boolean isPublic;
}