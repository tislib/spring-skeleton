package net.tislib.springskeleton.service;

import net.tislib.springskeleton.resource.CountryResource;
import net.tislib.springskeleton.resource.base.BaseResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CountryService {
    Page<BaseResource> list(Pageable pageable, Optional<CountryResource.Projection> projection);

    BaseResource get(Integer id, Optional<CountryResource.Projection> projection);

    BaseResource create(CountryResource country, Optional<CountryResource.Projection> projection);

    BaseResource update(Integer id, CountryResource country, Optional<CountryResource.Projection> projection);

    void delete(Integer id);
}
