package com.timesoft.hr.employeedata.service;

import com.timesoft.hr.employeedata.resource.CountryResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {
    Page<CountryResource> list(Pageable pageable);

    CountryResource get(Integer id);

    CountryResource create(CountryResource country);

    CountryResource update(Integer id, CountryResource country);
}
