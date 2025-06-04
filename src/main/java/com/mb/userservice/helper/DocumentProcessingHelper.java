package com.mb.userservice.helper;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DocumentProcessingHelper {

    @Value("${file.base.path}")
    private String basePath;

    public Resource downloadFile(Long documentId) {
        File dir = new File(basePath + documentId);
        Resource resource = null;
        try {
            if (dir.exists()) {
                resource = new UrlResource(dir.toURI());
                return resource;
            }
        } catch (Exception e) {
            log.error("downloadFile | error while downloading file.");
            return resource;
        }
        return resource;
    }
}