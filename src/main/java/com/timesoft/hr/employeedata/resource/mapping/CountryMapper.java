package com.timesoft.hr.employeedata.resource.mapping;

import com.timesoft.hr.employeedata.data.Country;
import com.timesoft.hr.employeedata.resource.CountryResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    Country fromResource(CountryResource country);

    CountryResource toResource(Country country);
}

