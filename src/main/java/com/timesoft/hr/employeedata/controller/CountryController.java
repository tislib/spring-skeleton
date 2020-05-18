package com.timesoft.hr.employeedata.controller;

import com.timesoft.hr.employeedata.resource.CountryResource;
import com.timesoft.hr.employeedata.resource.CountryResource.Projection;
import com.timesoft.hr.employeedata.resource.base.BaseProjection;
import com.timesoft.hr.employeedata.resource.base.BaseResource;
import com.timesoft.hr.employeedata.service.CountryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.timesoft.hr.employeedata.constants.ApiConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_COUNTRIES)
public class CountryController {

    private final CountryService service;

    @ApiOperation(value = "Gets country list")
    @GetMapping
    public Page<BaseResource> list(Pageable pageable,
                                   @RequestParam(value = PARAM_PROJECTION) final Optional<Projection> projection) {
        return service.list(pageable, projection);
    }

    @GetMapping(API_ID_RESOURCE)
    @ApiOperation(value = "Gets country by id")
    public ResponseEntity<BaseResource> get(@PathVariable Integer id,
                                 @RequestParam(value = PARAM_PROJECTION) final Optional<Projection> projection) {
        return ResponseEntity.ok(service.get(id, projection));
    }

    @DeleteMapping(API_ID_RESOURCE)
    @ApiOperation(value = "Deletes country by id")
    public ResponseEntity<BaseResource> delete(@PathVariable Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ApiOperation(value = "Create country")
    public ResponseEntity<BaseResource> create(@RequestBody CountryResource country,
                                    @RequestParam(value = PARAM_PROJECTION) final Optional<Projection> projection) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(country, projection));
    }

    @PatchMapping(API_ID_RESOURCE)
    @ApiOperation(value = "Update country")
    public ResponseEntity<BaseResource> update(@PathVariable Integer id,
                                    @RequestBody CountryResource country,
                                    @RequestParam(value = PARAM_PROJECTION) final Optional<Projection> projection) {
        return ResponseEntity.ok(service.update(id, country, projection));
    }

}
