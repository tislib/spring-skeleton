package com.timesoft.hr.employeedata.controller;

import com.timesoft.hr.employeedata.resource.CountryResource;
import com.timesoft.hr.employeedata.service.CountryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> get(@PathVariable Integer id) {
        return ResponseEntity.ok(service.get(id));
    }

    @DeleteMapping(API_ID_RESOURCE)
    @ApiOperation(value = "Deletes country by id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ApiOperation(value = "Create country")
    public ResponseEntity<?> create(@RequestBody CountryResource country) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(country));
    }

    @PatchMapping(API_ID_RESOURCE)
    @ApiOperation(value = "Update country")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CountryResource country) {
        return ResponseEntity.ok(service.update(id, country));
    }

}
