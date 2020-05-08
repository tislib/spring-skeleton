package com.timesoft.hr.employeedata.resource.mapping;

import com.timesoft.hr.employeedata.data.Country;
import com.timesoft.hr.employeedata.resource.CountryResource;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-08T17:22:55+0400",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.6 (JetBrains s.r.o)"
)
@Component
public class CountryMapperImpl implements CountryMapper {

    @Override
    public Country fromResource(CountryResource country) {
        if ( country == null ) {
            return null;
        }

        Country country1 = new Country();

        country1.setId( country.getId() );
        country1.setCode( country.getCode() );
        country1.setIsoCode( country.getIsoCode() );
        country1.setIso3Code( country.getIso3Code() );
        country1.setName( country.getName() );
        country1.setNationality( country.getNationality() );

        return country1;
    }

    @Override
    public CountryResource toResource(Country country) {
        if ( country == null ) {
            return null;
        }

        CountryResource countryResource = new CountryResource();

        countryResource.setId( country.getId() );
        countryResource.setCode( country.getCode() );
        countryResource.setIsoCode( country.getIsoCode() );
        countryResource.setIso3Code( country.getIso3Code() );
        countryResource.setName( country.getName() );
        countryResource.setNationality( country.getNationality() );

        return countryResource;
    }
}
