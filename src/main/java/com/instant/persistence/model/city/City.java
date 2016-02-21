package com.instant.persistence.model.city;

import com.instant.persistence.model.NamedModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author sroshchupkin
 */


@Data
public class City extends NamedModel{
    private List<String> zip;
}
