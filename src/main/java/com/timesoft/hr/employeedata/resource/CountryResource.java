package com.timesoft.hr.employeedata.resource;

import com.timesoft.hr.employeedata.resource.base.Resource;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
public class CountryResource extends Resource implements CountryUpdate {

    @Id
    private Integer id;

    private int code;

    @NotNull
    private String isoCode;

    @NotNull
    private String iso3Code;

    @NotNull
    private String name;

    @NotNull
    private String nationality;
}
