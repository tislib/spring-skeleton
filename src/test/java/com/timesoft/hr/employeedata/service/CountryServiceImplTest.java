package com.timesoft.hr.employeedata.service;

import com.timesoft.hr.employeedata.data.Country;
import com.timesoft.hr.employeedata.data.CountryRepository;
import com.timesoft.hr.employeedata.resource.CountryResource;
import com.timesoft.hr.employeedata.resource.base.Resource;
import com.timesoft.hr.employeedata.resource.mapping.CountryMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void list() {
        Pageable pageable = Pageable.unpaged();
        Country country = new Country();
        country.setName("sample-country");
        Page<Country> pagedData = new PageImpl<>(Collections.singletonList(country));
        CountryResource countryResource = new CountryResource();
        countryResource.setName("sample-country");
        Page<Resource> expectedData = new PageImpl<>(Collections.singletonList(countryResource));

        when(repository.findAll(pageable)).thenReturn(pagedData);

        Page<CountryResource> result = service.list(pageable);

        assertEquals(expectedData, result);
    }

    @Test
    void get() {
        int id = 3;

        Country country = new Country();
        country.setName("sample-country");

        CountryResource countryResource = new CountryResource();
        countryResource.setName("sample-country");

        when(repository.getOne(id)).thenReturn(country);

        CountryResource result = service.get(id);

        assertEquals(countryResource, result);
    }

    @Test
    void create() {
        Country country = new Country();
        country.setName("sample-country");

        CountryResource countryResource = new CountryResource();
        countryResource.setName("sample-country");

        when(repository.save(country)).thenReturn(country);

        CountryResource result = service.create(countryResource);

        assertEquals(countryResource, result);
    }

    @Test
    void update() {
        int id = 3;

        Country country = new Country();
        country.setName("sample-country");

        CountryResource countryResource = new CountryResource();
        countryResource.setName("sample-country");
        countryResource.setFields(Collections.singleton("name"));

        when(repository.getOne(id)).thenReturn(country);
        when(repository.save(country)).thenReturn(country);

        CountryResource result = service.update(id, countryResource);

        countryResource.setFields(null);
        assertEquals(countryResource, result);
    }
}