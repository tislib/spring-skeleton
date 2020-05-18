package com.timesoft.hr.employeedata.controller;

import com.timesoft.hr.employeedata.resource.CountryResource;
import com.timesoft.hr.employeedata.resource.base.BaseResource;
import com.timesoft.hr.employeedata.resource.projection.IdAndNameProjection;
import com.timesoft.hr.employeedata.service.CountryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryControllerTest {

    @InjectMocks
    CountryController controller;

    @Mock
    CountryService service;

    @Test
    public void shouldListCountries() {
        Pageable pageable = Pageable.unpaged();
        Page<BaseResource> pageData = new PageImpl<>(new ArrayList<>());

        when(service.list(pageable, Optional.of(CountryResource.Projection.ID_AND_NAME))).thenReturn(pageData);

        Page<BaseResource> result = controller.list(pageable, Optional.of(CountryResource.Projection.ID_AND_NAME));

        assertEquals(pageData, result);
    }

    @Test
    public void shouldCreateCountry() {
        CountryResource resource = new CountryResource();

        when(service.create(resource, Optional.of(CountryResource.Projection.ID_AND_NAME))).thenReturn(resource);

        ResponseEntity<?> result = controller.create(resource, Optional.of(CountryResource.Projection.ID_AND_NAME));

        assertEquals(resource, result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void shouldUpdateCountry() {
        CountryResource resource = new CountryResource();
        int id = 1;

        when(service.update(id, resource, Optional.of(CountryResource.Projection.ID_AND_NAME))).thenReturn(resource);

        ResponseEntity<?> result = controller.update(id, resource, Optional.of(CountryResource.Projection.ID_AND_NAME));

        assertEquals(resource, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void shouldGetCountryById() {
        CountryResource resource = new CountryResource();
        int id = 1;

        when(service.get(id, Optional.of(CountryResource.Projection.ID_AND_NAME))).thenReturn(resource);

        ResponseEntity<?> result = controller.get(id, Optional.of(CountryResource.Projection.ID_AND_NAME));

        assertEquals(resource, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void shouldDeleteCountry() {
        int id = 1;

        ResponseEntity<?> result = controller.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

}