package com.timesoft.hr.employeedata.resource.base;

import lombok.Data;

import java.util.Set;

@Data
public class Resource implements BaseResource {

    private Set<String> fields;
}
