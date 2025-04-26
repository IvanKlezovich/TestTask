package com.example.testtask.controller;

import com.example.testtask.configure.IntegrationTest;
import com.example.testtask.configure.TestcontainersConfiguration;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.example.testtask.util.JsonUtils.getFromFile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Nested
    class GetUsersByFilter {
        private static final String USERS_URL = "/search/";
        private static final String DATE_OF_BIRTH = "1990-01-01";
        private static final String PHONE = "1234567890";
        private static final String NAME = "John";
        private static final String EMAIL = "john@example.com";
        private static final String PAGE = "0";
        private static final String SIZE = "10";

        @Test
        @SneakyThrows
        void shouldReturnFilteredUsersWithAllParametersAndStatus200() {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("dateOfBirth", DATE_OF_BIRTH);
            params.add("phone", PHONE);
            params.add("name", NAME);
            params.add("email", EMAIL);
            params.add("page", PAGE);
            params.add("size", SIZE);
            String jsonResponse = getFromFile("src/test/resources/response/search_withoutFilters.json");

            mockMvc.perform(get(USERS_URL)
                            //.header(AUTHORIZATION, PREFIX_TOKEN + WHITESPACE_STRING + token)
                            .params(params))
                    .andExpectAll(
                            status().isOk());
//                            content().json(jsonResponse));
        }

        @Test
        @SneakyThrows
        void shouldReturnPaginatedResponseWithDefaultValues() {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("page", "0");
            params.add("size", "10");
            String jsonResponse = getFromFile("src/test/resources/response/search_withFilters.json");

            mockMvc.perform(get(USERS_URL)
                            //.header(AUTHORIZATION, PREFIX_TOKEN + WHITESPACE_STRING + token)
                            .params(params))
                    .andExpectAll(
                            status().isOk());
//                            content().json(jsonResponse));
        }

        @Test
        @SneakyThrows
        void shouldReturnBadRequestWhenDateOfBirthIsInvalid() {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("dateOfBirth", "invalid-date");

            mockMvc.perform(get(USERS_URL)
                            //.header(AUTHORIZATION, PREFIX_TOKEN + WHITESPACE_STRING + token)
                            .params(params))
                    .andExpect(status().isBadRequest());
        }
    }
}
