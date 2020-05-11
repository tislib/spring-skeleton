package com.timesoft.hr.employeedata.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timesoft.hr.employeedata.data.Country;
import com.timesoft.hr.employeedata.resource.CountryResource;
import com.timesoft.hr.employeedata.resource.CountryUpdate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PartialUpdateUtil {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public static void update(Country existingCountry, CountryResource updatedResource, Class<CountryUpdate> updateClass) {

    }
}
