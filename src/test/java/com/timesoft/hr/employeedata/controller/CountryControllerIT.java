package com.timesoft.hr.employeedata.controller;

import com.timesoft.hr.employeedata.base.BaseIntegrationTest;
import com.timesoft.hr.employeedata.resource.CountryResource;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultMatcher;

import static com.timesoft.hr.employeedata.constants.ApiConstants.API_COUNTRIES;
import static com.timesoft.hr.employeedata.constants.ApiConstants.API_ID_RESOURCE;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CountryControllerIT extends BaseIntegrationTest {

    @Test
    public void shouldListCountries() throws Exception {
        this.mockMvc.perform(get(API_COUNTRIES))
                .andExpect(status().isOk())
                .andExpect(matchAll(defaultCountryMatchers("$.content[0]")));
    }

    @Test
    public void shouldGetCountryById() throws Exception {
        this.mockMvc.perform(get(API_COUNTRIES + "/" + API_ID_RESOURCE, 1))
                .andExpect(status().isOk())
                .andExpect(matchAll(defaultCountryMatchers("$")));
    }

    @Test
    public void shouldDeleteCountry() throws Exception {
        this.mockMvc.perform(delete(API_COUNTRIES + "/" + API_ID_RESOURCE, 2))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldCreateCountry() throws Exception {
        CountryResource countryResource = new CountryResource();
        countryResource.setName("test-country");
        countryResource.setCode(123);
        countryResource.setIsoCode("aa");
        countryResource.setIso3Code("aaa");
        countryResource.setNationality("test-nationality");

        this.mockMvc.perform(post(API_COUNTRIES)
                .content(body(countryResource))
                .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(matchAll(
                        jsonPath("$." + CountryResource.Fields.code, is(123)),
                        jsonPath("$." + CountryResource.Fields.isoCode, is("aa")),
                        jsonPath("$.iso3Code", is("aaa")),
                        jsonPath("$.name", is("test-country")),
                        jsonPath("$.nationality", is("test-nationality"))
                ));
    }

    private ResultMatcher[] defaultCountryMatchers(String prefix) {
        return new ResultMatcher[]{
                jsonPath(prefix + ".code", is(994)),
                jsonPath(prefix + ".isoCode", is("az")),
                jsonPath(prefix + ".iso3Code", is("aze")),
                jsonPath(prefix + ".name", is("Azerbaijan")),
                jsonPath(prefix + ".nationality", is("Azerbaijani")),
        };
    }
}
