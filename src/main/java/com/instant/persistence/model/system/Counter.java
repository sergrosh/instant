package com.instant.persistence.model.system;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * @author Sergii Roshchupkin
 */
@Data
@Document(collection = "counters")
public class Counter {
    @Id
    private String id;
    private int seq;
}
