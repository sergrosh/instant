package com.instant.persistence.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author sroshchupkin
 */
@Data
public abstract class BaseModel implements Serializable {
    @Id
    private String id;
}
