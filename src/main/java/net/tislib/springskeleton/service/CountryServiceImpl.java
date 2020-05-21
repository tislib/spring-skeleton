package net.tislib.springskeleton.service;

import net.tislib.springskeleton.data.Country;
import net.tislib.springskeleton.data.CountryRepository;
import net.tislib.springskeleton.resource.CountryResource;
import net.tislib.springskeleton.resource.CountryUpdate;
import net.tislib.springskeleton.resource.base.BaseResource;
import net.tislib.springskeleton.resource.mapping.CountryMapper;
import net.tislib.springskeleton.util.PartialUpdateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repository;
    private final CountryMapper mapper;
    private final ProjectionFactory projectionFactory;

    @Override
    public Page<BaseResource> list(Pageable pageable, Optional<CountryResource.Projection> projection) {
        return repository.findAll(pageable)
                .map(mapper::toResource)
                .map(item -> toProjection(projection, item));
    }

    private <T extends BaseResource> BaseResource toProjection(Optional<CountryResource.Projection> projection, T item) {
        return projection.isPresent() ? projectionFactory.createProjection(projection.get().getProjectionClass(), item) : item;
    }

    @Override
    public BaseResource get(Integer id, Optional<CountryResource.Projection> projection) {
        Country country = repository.getOne(id);
        CountryResource resource = mapper.toResource(country);

        return toProjection(projection, resource);
    }

    @Override
    public void delete(Integer id) {
        Country country = repository.getOne(id);
        repository.delete(country);
    }

    @Override
    public BaseResource create(CountryResource countryResource, Optional<CountryResource.Projection> projection) {
        PartialUpdateUtil.validate(countryResource, countryResource, CountryResource.class);

        Country country = mapper.fromResource(countryResource);

        Country updatedCountry = repository.save(country);

        return get(updatedCountry.getId(), projection);
    }

    @Override
    public BaseResource update(Integer id, CountryResource updatedResource, Optional<CountryResource.Projection> projection) {
        Country existingCountry = repository.getOne(id);
        CountryResource existingResource = mapper.toResource(existingCountry);

        PartialUpdateUtil.update(existingResource, updatedResource);

        PartialUpdateUtil.validate(existingResource, updatedResource, CountryUpdate.class);

        Country countryForUpdate = mapper.fromResource(existingResource);
        repository.save(countryForUpdate);

        return get(id, projection);
    }
}
