package com.timesoft.hr.employeedata.controller;

import com.timesoft.hr.employeedata.resource.CountryResource;
import com.timesoft.hr.employeedata.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static com.timesoft.hr.employeedata.constants.ApiConstants.API_COUNTRIES;
import static com.timesoft.hr.employeedata.constants.ApiConstants.API_ID_RESOURCE;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_COUNTRIES)
public class CountryController {

    private final CountryService service;

    @GetMapping
    public Page<CountryResource> list(@RequestParam Pageable pageable) {
        return service.list(pageable);
    }

    @GetMapping(API_ID_RESOURCE)
    public CountryResource get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PostMapping
    public CountryResource create(@RequestBody CountryResource country) {
        return service.create(country);
    }

    @PatchMapping(API_ID_RESOURCE)
    public CountryResource update(@PathVariable Integer id, @RequestBody CountryResource country) {
        return service.update(id, country);
    }

}
