package com.timesoft.hr.employeedata.resource.projection;

import com.timesoft.hr.employeedata.resource.base.BaseProjection;

public interface IdAndNameProjection extends BaseProjection {
    Number getId();

    String getName();
}
