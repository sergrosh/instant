package com.instant.persistence.model.city;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author sroshchupkin
 */


@Document
@ToString(exclude="id")
@EqualsAndHashCode(exclude="id")
public class City {
    @Id
    @Getter @Setter private String id;
    @Getter @Setter private String name;
    @Getter @Setter private List<String> zip;
}
