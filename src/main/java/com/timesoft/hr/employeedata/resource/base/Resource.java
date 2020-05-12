package com.timesoft.hr.employeedata.resource.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Set;

@Data
public class Resource implements BaseResource {
    @JsonIgnore
    private Set<String> fields;
}
