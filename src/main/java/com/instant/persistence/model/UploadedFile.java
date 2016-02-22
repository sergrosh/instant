package com.instant.persistence.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author sroshchupkin
 */

@Data
@Document
public class UploadedFile extends NamedModel{
    private String location;
    private Long size;
    private String type;
}
