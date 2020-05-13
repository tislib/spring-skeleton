package com.timesoft.hr.employeedata.controller;

import com.timesoft.hr.employeedata.resource.CountryResource;
import com.timesoft.hr.employeedata.service.CountryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.timesoft.hr.employeedata.constants.ApiConstants.API_COUNTRIES;
import static com.timesoft.hr.employeedata.constants.ApiConstants.API_ID_RESOURCE;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_COUNTRIES)
public class CountryController {

    private final CountryService service;

    @ApiOperation(value = "Gets country list")
    @GetMapping
    public Page<CountryResource> list(Pageable pageable) {
        return service.list(pageable);
    }

    @GetMapping(API_ID_RESOURCE)
    @ApiOperation(value = "Gets country by id")
    public CountryResource get(@PathVariable Integer id) {
        return service.get(id);
    }

    @DeleteMapping(API_ID_RESOURCE)
    @ApiOperation(value = "Deletes country by id")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PostMapping
    @ApiOperation(value = "Create country")
    public CountryResource create(@Validated @RequestBody CountryResource country) {
        return service.create(country);
    }

    @PatchMapping(API_ID_RESOURCE)
    @ApiOperation(value = "Update country")
    public CountryResource update(@PathVariable Integer id, @RequestBody CountryResource country) {
        return service.update(id, country);
    }

}
