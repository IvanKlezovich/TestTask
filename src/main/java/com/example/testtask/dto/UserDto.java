package com.example.testtask.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("birthday")
  private LocalDate birthday;

  @JsonProperty("phoneData")
  private List<PhoneDataDto> phoneData;

  @JsonProperty("emailData")
  private List<EmailDataDto> emailData;

  @JsonProperty("balance")
  private BigDecimal balance;
}
