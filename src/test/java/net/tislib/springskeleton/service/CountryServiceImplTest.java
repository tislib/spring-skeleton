package net.tislib.springskeleton.service;

import net.tislib.springskeleton.data.Country;
import net.tislib.springskeleton.data.CountryRepository;
import net.tislib.springskeleton.resource.CountryResource;
import net.tislib.springskeleton.resource.base.BaseResource;
import net.tislib.springskeleton.resource.base.Resource;
import net.tislib.springskeleton.resource.mapping.CountryMapper;
import com.timesoft.hr.employeedata.resource.mapping.CountryMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

    @InjectMocks
    CountryServiceImpl service;

    @Mock
    CountryRepository repository;

    @Spy
    CountryMapper mapper = new CountryMapperImpl();

    @Test
    void shouldListCountries() {
        Pageable pageable = Pageable.unpaged();
        Country country = new Country();
        country.setName("sample-country");
        Page<Country> pagedData = new PageImpl<>(Collections.singletonList(country));
        CountryResource countryResource = new CountryResource();
        countryResource.setName("sample-country");
        Page<Resource> expectedData = new PageImpl<>(Collections.singletonList(countryResource));

        when(repository.findAll(pageable)).thenReturn(pagedData);

        Page<BaseResource> result = service.list(pageable, Optional.empty());

        assertEquals(expectedData, result);
    }

    @Test
    void shouldGetCountryById() {
        int id = 3;

        Country country = createDummyCountry();

        CountryResource countryResource = createDummyCountryResource();

        when(repository.getOne(id)).thenReturn(country);

        BaseResource result = service.get(id, Optional.empty());

        assertEquals(countryResource, result);
    }

    private CountryResource createDummyCountryResource() {
        CountryResource resource = new CountryResource();
        resource.setName("sample-country");
        resource.setCode(994);
        resource.setIso3Code("aze");
        resource.setIsoCode("az");
        resource.setNationality("sample-nationality");
        return resource;
    }

    private Country createDummyCountry() {
        Country country = new Country();
        country.setName("sample-country");
        country.setCode(994);
        country.setIso3Code("aze");
        country.setIsoCode("az");
        country.setNationality("sample-nationality");
        return country;
    }

    @Test
    void shouldCreateCountry() {
        Country country = createDummyCountry();
        country.setId(123);

        CountryResource countryResource = createDummyCountryResource();
        countryResource.setId(123);
        countryResource.setFields(Collections.singleton("name"));

        when(repository.save(country)).thenReturn(country);
        when(repository.getOne(123)).thenReturn(country);

        BaseResource result = service.create(countryResource, Optional.empty());

        countryResource.setFields(null);
        assertEquals(countryResource, result);
    }

    @Test
    void shouldUpdateCountry() {
        int id = 3;

        Country country = createDummyCountry();

        CountryResource countryResource = createDummyCountryResource();

        countryResource.setFields(Collections.singleton("name"));

        when(repository.getOne(id)).thenReturn(country);
        when(repository.save(country)).thenReturn(country);

        BaseResource result = service.update(id, countryResource, Optional.empty());

        countryResource.setFields(null);
        assertEquals(countryResource, result);
    }

    @Test
    void shouldDeleteCountry() {
        int id = 3;

        Country country = createDummyCountry();

        when(repository.getOne(id)).thenReturn(country);
        doNothing().when(repository)
                .delete(country);

        service.delete(id);

    }
}