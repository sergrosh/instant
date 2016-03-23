package com.instant.api.model.venue;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
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

    @ApiModelProperty(required = false, value = "")
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

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("whatsapp")
    private String whatsApp;

    @ApiModelProperty(required = true, value = "")
    @JsonProperty("facebookpage")
    private String facebookPage;


    @ApiModelProperty(required = false, value = "")
    @JsonProperty("website")
    private String website;

    @ApiModelProperty(required = true, value = "")
    @JsonProperty("address")
    private String address;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("imagepath")
    private String imagePath;

    @ApiModelProperty(value = "")
    @JsonProperty("description")
    @Length(min = 3, max = 600, message = "Description should be at least 10 characters but less than 600")
    @NotEmpty
    private String description;

    @ApiModelProperty(required = true, value = "")
    @JsonProperty("speciality")
    private List<String> speciality;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("avgprice")
    private Double avgPrice;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("specialities")
    private String specialities;

//    @ApiModelProperty(value = "opentime")
//    @JsonSerialize(using = CustomTimeSerializer.class)
//    @JsonDeserialize(using = CustomTimeDeserializer.class)
//    @JsonProperty("opentime")
//    private LocalTime openTime;
//
//    @ApiModelProperty(value = "closetime")
//    @JsonSerialize(using = CustomTimeSerializer.class)
//    @JsonDeserialize(using = CustomTimeDeserializer.class)
//    @JsonProperty("closetime")
//    private LocalTime closeTime;


    @ApiModelProperty(value = "openhours")
    @JsonProperty("openhours")
    private String openHours;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("parking")
    private String parkingDetails;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("wifi")
    private Boolean wifiAvailability;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("smoking")
    private Boolean smokingAvailability;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("disabled")
    private Boolean accessForDisabled;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("takeaway")
    private Boolean takeAwayAvailability;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("outdoor")
    private String outdoorEnvironment;

    @ApiModelProperty(required = false, value = "")
    @JsonProperty("kidsplayground")
    private String kidsPlayground;
}
