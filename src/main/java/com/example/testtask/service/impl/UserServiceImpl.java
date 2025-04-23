package com.example.testtask.service.impl;

import com.example.testtask.dto.UserDto;
import com.example.testtask.repository.UserRepository;
import com.example.testtask.service.UserService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public Page<UserDto> getUsersDtoByFilterData(LocalDate dateOfBirth, String phone, String name,
      String email,
      Integer page, Integer size) {

    Pageable pageable = PageRequest.of(page, size);
    return userRepository.findAllWithFilters(name, email, phone, dateOfBirth, pageable);
  }

  public List<UserDto> getAllUsers() {
    return List.of();
  }

  public UserDto getUser(Long userId) {
    return null;
  }
}
