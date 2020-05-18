package com.timesoft.hr.employeedata.resource.base;

public interface ProjectionName {
    String name();

    Class<? extends BaseProjection> getProjectionClass();
}
