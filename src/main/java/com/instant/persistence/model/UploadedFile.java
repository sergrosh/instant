package com.instant.persistence.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author sroshchupkin
 */

@Data
@Document
public class UploadedFile extends NamedModel {
    private String location;
    private Long size;
    private String type;
}
