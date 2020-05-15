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
                        jsonPath("$." + CountryResource.Fields.iso3Code, is("aaa")),
                        jsonPath("$." + CountryResource.Fields.name, is("test-country")),
                        jsonPath("$." + CountryResource.Fields.nationality, is("test-nationality"))
                ));
    }

    @Test
    public void shouldUpdateCountry() throws Exception {
        CountryResource countryResource = new CountryResource();
        countryResource.setName("test-country");

        this.mockMvc.perform(patch(API_COUNTRIES + "/" + API_ID_RESOURCE, 2)
                .content(body(countryResource))
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(matchAll(
                        jsonPath("$." + CountryResource.Fields.code, is(123)),
                        jsonPath("$." + CountryResource.Fields.isoCode, is("az")),
                        jsonPath("$." + CountryResource.Fields.iso3Code, is("aze")),
                        jsonPath("$." + CountryResource.Fields.name, is("test-country")),
                        jsonPath("$." + CountryResource.Fields.nationality, is("Azerbaijani"))
                ));
    }

    private ResultMatcher[] defaultCountryMatchers(String prefix) {
        return new ResultMatcher[]{
                jsonPath(prefix + "." + CountryResource.Fields.code, is(994)),
                jsonPath(prefix + "." + CountryResource.Fields.isoCode, is("az")),
                jsonPath(prefix + "." + CountryResource.Fields.iso3Code, is("aze")),
                jsonPath(prefix + "." + CountryResource.Fields.name, is("Azerbaijan")),
                jsonPath(prefix + "." + CountryResource.Fields.nationality, is("Azerbaijani")),
        };
    }
}
