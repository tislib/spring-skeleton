package com.timesoft.hr.employeedata.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timesoft.hr.employeedata.data.Country;
import com.timesoft.hr.employeedata.data.CountryRepository;
import com.timesoft.hr.employeedata.resource.CountryResource;
import com.timesoft.hr.employeedata.resource.CountryUpdate;
import com.timesoft.hr.employeedata.resource.mapping.CountryMapper;
import com.timesoft.hr.employeedata.util.PartialUpdateUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repository;
    private final CountryMapper mapper;

    @Override
    public Page<CountryResource> list(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResource);
    }

    @Override
    public CountryResource get(Integer id) {
        Country country = repository.getOne(id);
        return mapper.toResource(country);
    }

    @Override
    public CountryResource create(CountryResource countryResource) {
        Country country = mapper.fromResource(countryResource);
        Country updatedCountry = repository.save(country);
        return mapper.toResource(updatedCountry);
    }

    @Override
    @SneakyThrows
    public CountryResource update(Integer id, CountryResource updatedResource) {
        Country existingCountry = repository.getOne(id);
        CountryResource existingResource = mapper.toResource(existingCountry);

        PartialUpdateUtil.update(existingCountry, updatedResource, CountryUpdate.class);

        Country countryForUpdate = mapper.fromResource(existingResource);
        repository.save(countryForUpdate);

        return get(id);
    }
}