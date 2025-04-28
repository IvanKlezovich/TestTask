package com.example.testtask.controller;

import static com.example.testtask.util.JsonUtils.getFromFile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.testtask.configure.IntegrationTest;
import com.example.testtask.configure.TestcontainersConfiguration;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@IntegrationTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
class UserControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Nested
  class GetUsersByFilter {

    private static final String LOGIN_URL = "/api/auth/login";
    private static final String USERS_URL = "/api/user/search/";
    private static final String NAME = "Дми";
    private static final String PAGE = "0";
    private static final String SIZE = "10";
    private static final String AUTHORIZATION = "Authorization";
    private static final String PREFIX_TOKEN = "Bearer ";
    private String token;

    @BeforeEach
    void setUp() throws Exception {
      // Получаем токен перед каждым тестом
      token = obtainToken();
    }

    private String obtainToken() throws Exception {
      MvcResult loginResult = mockMvc.perform(post(LOGIN_URL)
              .contentType(MediaType.APPLICATION_JSON)
              .content(getFromFile("src/test/resources/request/authorization.json")))
          .andExpect(status().isOk())
          .andReturn();

      String responseBody = loginResult.getResponse().getContentAsString();
      return responseBody.substring(10, responseBody.length() - 2);
    }

    @Test
    @SneakyThrows
    void shouldReturnFilteredUsersWithAllParametersAndStatus200() {
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("name", NAME);
      params.add("page", PAGE);
      params.add("size", SIZE);
      String jsonResponse = getFromFile("src/test/resources/response/search_withFilters.json");

      mockMvc.perform(get(USERS_URL)
              .header(AUTHORIZATION, PREFIX_TOKEN + token)
              .params(params))
          .andExpectAll(
              status().isOk(),
              content().json(jsonResponse));
    }

    @Test
    @SneakyThrows
    void shouldReturnPaginatedResponseWithDefaultValues() {
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("page", "0");
      params.add("size", "10");
      String jsonResponse = getFromFile("src/test/resources/response/search_withoutFilters.json");

      mockMvc.perform(get(USERS_URL)
              .header(AUTHORIZATION, PREFIX_TOKEN + token)
              .params(params))
          .andExpectAll(
              status().isOk(),
              content().json(jsonResponse));
    }

    @Test
    @SneakyThrows
    void shouldReturnBadRequestWhenDateOfBirthIsInvalid() {
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("dateOfBirth", "invalid-date");

      mockMvc.perform(get(USERS_URL)
              .header(AUTHORIZATION, PREFIX_TOKEN + token)
              .params(params))
          .andExpect(status().isBadRequest());
    }
  }
}
