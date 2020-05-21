package net.tislib.springskeleton.resource.mapping;

import net.tislib.springskeleton.data.Country;
import net.tislib.springskeleton.resource.CountryResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    Country fromResource(CountryResource country);

    @Mapping(target = "fields", ignore = true)
    CountryResource toResource(Country country);
}

