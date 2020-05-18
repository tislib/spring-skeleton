package com.timesoft.hr.employeedata.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.timesoft.hr.employeedata.resource.base.BaseProjection;
import com.timesoft.hr.employeedata.resource.base.ProjectionName;
import com.timesoft.hr.employeedata.resource.base.Resource;
import com.timesoft.hr.employeedata.resource.projection.IdAndNameProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@ApiModel("Country")
@JsonInclude(NON_NULL)
public class CountryResource extends Resource implements CountryUpdate {

    @RequiredArgsConstructor
    @Getter
    public enum Projection implements ProjectionName {
        ID_AND_NAME(IdAndNameProjection.class);

        private final Class<? extends BaseProjection> projectionClass;
    }

    @Id
    @ApiModelProperty("Id")
    private Integer id;

    @ApiModelProperty("Code")
    private Integer code;

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
