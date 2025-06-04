package com.mb.userservice.service;

import java.util.List;
import java.util.stream.Collectors;

import com.mb.userservice.model.entity.DocumentEntity;
import com.mb.userservice.model.request.CreateDocumentRequest;
import com.mb.userservice.model.request.UpdateDocumentRequest;
import com.mb.userservice.model.response.DocumentObj;
import com.mb.userservice.model.response.DocumentResponse;

public interface DocumentService {

    DocumentObj getDocumentByDocumentId(Long documentId);

    DocumentResponse getDocumentByEntityId(Long id);

    DocumentResponse createDocument(Long documentId, CreateDocumentRequest request);

    DocumentResponse updateDocument(Long documentId, UpdateDocumentRequest request);

    String deleteDocument(Long documentId);

    default DocumentObj buildDocument(DocumentEntity entity) {
        /*
         * TO DO add more info to document object
         */
        return DocumentObj.builder().documentId(entity.getDocumentId()).build();
    }

    default List<DocumentObj> getDependents(List<DocumentEntity> documentEntities) {
        List<DocumentObj> documentObjs = documentEntities.stream().map(entity -> {
            DocumentObj documentObj = DocumentObj.builder()
                    /*
                     * TO.DO add more details to the documentObj object
                     */
                    .build();
            return documentObj;
        }).collect(Collectors.toList());
        return documentObjs;
    }

}