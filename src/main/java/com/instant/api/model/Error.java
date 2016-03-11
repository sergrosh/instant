package com.instant.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(description = "")
@Data
public class Error {

    @ApiModelProperty(required = true, value = "")
    @JsonProperty("code")
    private Integer code;

    @ApiModelProperty(required = true, value = "")
    @JsonProperty("message")
    private String message;

}
