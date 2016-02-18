package com.instant.persistence.repository;

import com.instant.persistence.model.UploadedFile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author sroshchupkin
 */
public interface UploadedFileRepository extends MongoRepository<UploadedFile, String> {
    List<UploadedFile> findFileByName(String fileName);
    List<UploadedFile> deleteByName(String filename);
    List<UploadedFile> findFileByType(String fileType);
}
