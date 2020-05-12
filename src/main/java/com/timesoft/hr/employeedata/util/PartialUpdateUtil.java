package com.timesoft.hr.employeedata.util;

import com.timesoft.hr.employeedata.resource.base.PartialUpdate;
import com.timesoft.hr.employeedata.resource.base.Resource;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class PartialUpdateUtil {

    private static final Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public static <T extends Resource> void update(T existingCountry, T updatedResource) {
        Set<String> ignoredProperties = new HashSet<>();
        ignoredProperties.add("id");

        ReflectionUtils.doWithFields(updatedResource.getClass(), field -> {
            if (!updatedResource.getFields().contains(field.getName())) {
                ignoredProperties.add(field.getName());
            }
        });

        BeanUtils.copyProperties(updatedResource, existingCountry, ignoredProperties.toArray(new String[0]));
    }

    public static <UT extends PartialUpdate, T extends Resource> void validate(T updatedResource, Class<UT> countryUpdateClass) {
        validator.validate(updatedResource);
    }
}
