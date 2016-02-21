package com.instant.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author sroshchupkin
 */

@Document
@ToString(exclude="id")
@EqualsAndHashCode(exclude="id")
public class UploadedFile {
    @Id
    @Getter @Setter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private String location;
    @Getter @Setter private Long size;
    @Getter @Setter private String type;
}
