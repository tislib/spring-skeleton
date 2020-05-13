package com.timesoft.hr.employeedata.resource;

import com.timesoft.hr.employeedata.resource.base.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@ApiModel("Country")
public class CountryResource extends Resource implements CountryUpdate {

    @Id
    @ApiModelProperty("Id")
    private Integer id;

    @ApiModelProperty("Code")
    private int code;

    @NotNull
    @ApiModelProperty("ISO Code with 2 letters")
    private String isoCode;

    @NotNull
    @ApiModelProperty("ISO CODE with 3 letters")
    private String iso3Code;

    @NotNull
    @ApiModelProperty("Name")
    private String name;

    @NotNull
    @ApiModelProperty("Nationality")
    private String nationality;
}
