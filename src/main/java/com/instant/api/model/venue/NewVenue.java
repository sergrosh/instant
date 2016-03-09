package com.instant.api.model.venue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.instant.api.model.serialization.CustomTimeDeserializer;
import com.instant.api.model.serialization.CustomTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalTime;
import org.springframework.data.mongodb.core.index.TextIndexed;

import java.io.Serializable;
import java.util.List;


@ApiModel(description = "")
@Data
public class NewVenue implements Serializable {

    @TextIndexed(weight = 3)
    @ApiModelProperty(required = true, value = "")
    @JsonProperty("name")
    @NotEmpty
    @Length(min = 3, max = 100, message = "Name should be at least 3 characters but less than 100")
    private String name;

    @ApiModelProperty(required = true, value = "")
    @JsonProperty("country")
    @NotEmpty
    private String country;

    @ApiModelProperty(required = true, value = "")
    @JsonProperty("city")
    @NotEmpty
    private String city;

    @ApiModelProperty(required = true, value = "")
    @JsonProperty("category")
    private String category;

    @ApiModelProperty(required = true, value = "")
    @JsonProperty("company")
    private String company;

    @ApiModelProperty(required = true, value = "")
    @JsonProperty("email")
    private String email;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("phoneprimary")
    private String phonePrimary;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("phonesecondary")
    private String phoneSecondary;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("mobileprimary")
    private String mobilePrimary;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("mobilesecondary")
    private String mobileSecondary;

    @ApiModelProperty(required = true, value = "")
    @JsonProperty("facebookpage")
    private String facebookPage;
    private String website;

    @ApiModelProperty(required = true, value = "")
    @JsonProperty("address")
    private String address;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("imagepath")
    private String imagePath;

    @ApiModelProperty(value = "")
    @JsonProperty("description")
    @Length(min = 10, max = 300, message = "Description should be at least 10 characters but less than 300")
    @NotEmpty
    private String description;

    @ApiModelProperty(required = true, value = "")
    @JsonProperty("speciality")
    private List<String> speciality;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("menu")
    private List<String> menu;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("gallery")
    private List<String> gallery;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("specialities")
    private String specialities;

    @ApiModelProperty(value = "opentime")
    @JsonSerialize(using = CustomTimeSerializer.class)
    @JsonDeserialize(using = CustomTimeDeserializer.class)
    @JsonProperty("opentime")
    private LocalTime openTime;

    @ApiModelProperty(value = "closetime")
    @JsonSerialize(using = CustomTimeSerializer.class)
    @JsonDeserialize(using = CustomTimeDeserializer.class)
    @JsonProperty("closetime")
    private LocalTime closeTime;
}
