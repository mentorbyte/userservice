package com.mb.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mb.userservice.helper.DocumentProcessingHelper;
import com.mb.userservice.model.request.UpdateDocumentRequest;
import com.mb.userservice.model.response.DocumentObj;
import com.mb.userservice.model.response.DocumentResponse;
import com.mb.userservice.service.DocumentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/documents")
@Tag(name = "Document API endpoints")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentProcessingHelper documentProcessingHelper;

    @Operation(summary = "Get document endpoint")
    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentObj> getUserById(@PathVariable Long documentId) {
        return ResponseEntity.ok(documentService.getDocumentByDocumentId(documentId));
    }

    @Operation(summary = "Update document metadata endpoint")
    @PutMapping("/{documentId}/metadata")
    public ResponseEntity<DocumentResponse> updateDocument(
            @PathVariable Long documentId, @Valid @RequestBody UpdateDocumentRequest request) {
        return ResponseEntity.ok(documentService.updateDocument(documentId, request));
    }


    @Operation(summary = "Delete document endpoint")
    @DeleteMapping("/{documentId}")
    public ResponseEntity<String> deleteDocument(@PathVariable Long documentId) {
        documentService.deleteDocument(documentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{documentId}/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)  
    public ResponseEntity<?> downloadFile(@PathVariable Long documentId){
        Resource file = documentProcessingHelper.downloadFile(documentId);
        if(file == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(file);
        }
    }
}