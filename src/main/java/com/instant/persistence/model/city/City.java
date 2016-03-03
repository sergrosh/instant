package com.instant.persistence.model.city;

import com.instant.persistence.model.NamedModel;
import lombok.Data;

import java.util.List;

/**
 * @author sroshchupkin
 */


@Data
public class City extends NamedModel {
    private List<String> zip;
}
