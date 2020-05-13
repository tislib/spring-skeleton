package com.timesoft.hr.employeedata.service;

import com.timesoft.hr.employeedata.data.Country;
import com.timesoft.hr.employeedata.data.CountryRepository;
import com.timesoft.hr.employeedata.resource.CountryResource;
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

import java.util.ArrayList;

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
        Page<Country> pagedData = new PageImpl<>(new ArrayList<>());

        when(repository.findAll(pageable)).thenReturn(pagedData);

        Page<CountryResource> result = service.list(pageable);
    }

    @Test
    void get() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }
}