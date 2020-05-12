package com.timesoft.hr.employeedata.controller;

import com.timesoft.hr.employeedata.base.BaseIntegrationTest;
import com.timesoft.hr.employeedata.constants.ApiConstants;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CountryControllerIT extends BaseIntegrationTest {

    @Test
    public void shouldCreateCountry() throws Exception {
        this.mockMvc.perform(get(ApiConstants.API_COUNTRIES))
                .andExpect(status().isOk());
    }
}
