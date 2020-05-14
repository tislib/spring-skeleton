package com.timesoft.hr.employeedata.controller;

import com.timesoft.hr.employeedata.resource.CountryResource;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryControllerTest {

    @InjectMocks
    CountryController controller;

    @Mock
    CountryService service;

    @Test
    public void list() {
        Pageable pageable = Pageable.unpaged();
        Page<CountryResource> pageData = new PageImpl<>(new ArrayList<>());

        when(service.list(pageable)).thenReturn(pageData);

        Page<CountryResource> result = controller.list(pageable);

        assertEquals(pageData, result);
    }

    @Test
    public void create() {
        CountryResource resource = new CountryResource();

        when(service.create(resource)).thenReturn(resource);

        ResponseEntity<?> result = controller.create(resource);

        assertEquals(resource, result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void update() {
        CountryResource resource = new CountryResource();
        int id = 1;

        when(service.update(id, resource)).thenReturn(resource);

        ResponseEntity<?> result = controller.update(id, resource);

        assertEquals(resource, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void get() {
        CountryResource resource = new CountryResource();
        int id = 1;

        when(service.get(id)).thenReturn(resource);

        ResponseEntity<?> result = controller.get(id);

        assertEquals(resource, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}