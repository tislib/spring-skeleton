package com.timesoft.hr.employeedata.resource.base;

import java.util.Set;

public interface BaseResource {

    Set<String> getFields();

    void setFields(Set<String> value);
}
