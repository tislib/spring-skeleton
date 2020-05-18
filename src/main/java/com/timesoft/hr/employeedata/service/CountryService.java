package com.timesoft.hr.employeedata.service;

import com.timesoft.hr.employeedata.resource.CountryResource;
import com.timesoft.hr.employeedata.resource.CountryResource.Projection;
import com.timesoft.hr.employeedata.resource.base.BaseResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CountryService {
    Page<BaseResource> list(Pageable pageable, Optional<Projection> projection);

    BaseResource get(Integer id, Optional<Projection> projection);

    BaseResource create(CountryResource country, Optional<Projection> projection);

    BaseResource update(Integer id, CountryResource country, Optional<Projection> projection);

    void delete(Integer id);
}
