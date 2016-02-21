package com.instant.persistence.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sroshchupkin
 */
public abstract class BaseModel implements Serializable {
    @Id
    @Getter @Setter private String id;
    @Getter @Setter private Date creationDate = new Date();
    @Getter @Setter private Date updateDate = new Date();
}
