package com.timesoft.hr.employeedata.resource.mapping;

import com.timesoft.hr.employeedata.data.Country;
import com.timesoft.hr.employeedata.resource.CountryResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    Country fromResource(CountryResource country);

    @Mapping(target = "fields", ignore = true)
    CountryResource toResource(Country country);
}

