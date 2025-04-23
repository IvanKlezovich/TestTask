package com.example.testtask.service;

import com.example.testtask.dto.UserDto;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;

public interface UserService {

  Page<UserDto> getUsersDtoByFilterData(LocalDate dateOfBirth,
      String phone,
      String name,
      String email,
      Integer page,
      Integer size);

  List<UserDto> getAllUsers();

  UserDto getUser(Long userId);
}
