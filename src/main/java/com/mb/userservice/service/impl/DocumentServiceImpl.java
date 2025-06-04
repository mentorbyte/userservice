package com.mb.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.mb.userservice.exception.ServiceException;
import com.mb.userservice.model.entity.DocumentEntity;
import com.mb.userservice.model.request.CreateDocumentRequest;
import com.mb.userservice.model.request.UpdateDocumentRequest;
import com.mb.userservice.model.response.DocumentObj;
import com.mb.userservice.model.response.DocumentResponse;
import com.mb.userservice.repository.DocumentRepository;
import com.mb.userservice.service.DocumentService;

@Component
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public DocumentObj getDocumentByDocumentId(Long documentId) {
        DocumentEntity documentEntity = findById(documentId);
        return buildDocument(documentEntity);
    }

    @Override
    public DocumentResponse getDocumentByEntityId(Long id) {
        List<DocumentEntity> documentEntities = documentRepository.findByEntityId(id);
        List<DocumentObj> documentObjs = getDependents(documentEntities);
        return DocumentResponse.builder().userId(id).documents(documentObjs).build();
    }

    @Override
    public DocumentResponse createDocument(Long documentId, CreateDocumentRequest request) {
        DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setDocumentType(request.getDocumentType());
        /*
         * To DO
         * Add more info to docuemnt entity
         */
        documentRepository.save(documentEntity);
        return buildDocumentResponse(documentEntity);
    }

    public DocumentResponse buildDocumentResponse(DocumentEntity entity) {
        List<DocumentObj> documents = new ArrayList<>();
        DocumentObj documentObj = buildDocument(entity);
        documents.add(documentObj);
        DocumentResponse addressResponse = DocumentResponse.builder()
                .userId(entity.getEntityId())
                .documents(documents)
                .build();
        return addressResponse;
    }

    @Override
    public DocumentResponse updateDocument(Long documentId, UpdateDocumentRequest request) {
        DocumentEntity documentEntity = findById(documentId);
        documentEntity.setDocumentType(request.getDocumentType());
         /*
         * To DO
         * Add more info to address entity
         */
        documentRepository.save(documentEntity);
        return buildDocumentResponse(documentEntity);
    }

    @Override
    public String deleteDocument(Long documentId) {
        DocumentEntity documentEntity = findById(documentId);
        documentRepository.delete(documentEntity);
        return "Record deleted successfully.";
    }

    private DocumentEntity findById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("document.user.record.not.found", HttpStatus.NOT_FOUND));
    }

}