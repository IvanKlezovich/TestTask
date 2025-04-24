package com.example.testtask.service;

import com.example.testtask.dto.PageResponseDto;
import com.example.testtask.dto.UserDto;
import java.time.LocalDate;

public interface UserService {

  PageResponseDto<UserDto> getUsersDtoByFilterData(LocalDate dateOfBirth,
      String phone,
      String name,
      String email,
      Integer page,
      Integer size);
}
