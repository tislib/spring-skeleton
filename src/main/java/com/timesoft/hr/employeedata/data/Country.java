package com.timesoft.hr.employeedata.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Country {

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
