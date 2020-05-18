package com.timesoft.hr.employeedata.util;

import com.timesoft.hr.employeedata.exception.DisallowedUpdateException;
import com.timesoft.hr.employeedata.resource.CountryResource;
import com.timesoft.hr.employeedata.resource.CountryUpdate;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PartialUpdateUtilTest {

    @Test
    void shouldUpdateOnlyAllowedFields() {
        CountryResource existingResource = createDummyResource();

        CountryResource updatedResource = new CountryResource();
        updatedResource.setName("sample-name-2");
        updatedResource.setCode(333);
        updatedResource.setFields(Collections.singleton("name"));

        PartialUpdateUtil.update(existingResource, updatedResource);

        assertEquals(existingResource.getName(), "sample-name-2");
        assertEquals(existingResource.getCode(), 123);
    }

    private CountryResource createDummyResource() {
        CountryResource existingResource = new CountryResource();
        existingResource.setName("sample-name");
        existingResource.setCode(123);
        existingResource.setNationality("sample-nationality");
        existingResource.setIso3Code("aaa");
        existingResource.setIsoCode("aa");
        existingResource.setId(123);
        return existingResource;
    }

    @Test
    void shouldFailValidationWhenDisallowedFieldSupplied() {

        CountryResource existingResource = createDummyResource();

        CountryResource updatedResource = new CountryResource();
        updatedResource.setName("sample-name-2");
        updatedResource.setCode(333);
        updatedResource.setFields(new HashSet<>(Arrays.asList("name", "code")));

        PartialUpdateUtil.update(existingResource, updatedResource);

        assertThrows(DisallowedUpdateException.class,
                () -> PartialUpdateUtil.validate(existingResource, updatedResource, CountryUpdate.class));
    }

    @Test
    void shouldFailValidationWhenFieldValidationFails() {
        CountryResource existingResource = createDummyResource();

        CountryResource updatedResource = new CountryResource();
        updatedResource.setCode(333);
        updatedResource.setFields(Collections.singleton("name"));

        PartialUpdateUtil.update(existingResource, updatedResource);

        assertThrows(ConstraintViolationException.class,
                () -> PartialUpdateUtil.validate(existingResource, updatedResource, CountryUpdate.class));
    }
}