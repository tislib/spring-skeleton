package com.timesoft.hr.employeedata.data;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "COUNTRIES")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COUNTRIES_ID_SEQ")
    @SequenceGenerator(name = "COUNTRIES_ID_SEQ",
            sequenceName = "COUNTRIES_ID_SEQ",
            allocationSize = 1)
    private Integer id;

    private int code;

    @NotNull
    @Column(name = "ISO_CODE")
    private String isoCode;

    @NotNull
    @Column(name = "ISO3_CODE")
    private String iso3Code;

    @NotNull
    private String name;

    @NotNull
    private String nationality;

}
