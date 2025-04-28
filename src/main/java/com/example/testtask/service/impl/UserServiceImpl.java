package com.example.testtask.service.impl;

import com.example.testtask.dto.PageResponseDto;
import com.example.testtask.dto.UserDto;
import com.example.testtask.entity.User;
import com.example.testtask.mapper.UserMapper;
import com.example.testtask.repository.UserRepository;
import com.example.testtask.service.UserService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Cacheable(value = "usersPage",
      key = "#root.target.getClass().getSimpleName() + ':' + #page + ':' + #size + ':' + #name + ':' + #email + ':' + #phone + ':' + #dateOfBirth")
  public PageResponseDto<UserDto> getUsersDtoByFilterData(LocalDate dateOfBirth, String phone,
      String name,
      String email, Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<User> usersPage = userRepository.findAllWithFilters(name, email, phone, dateOfBirth,
        pageable);
    return userMapper.toPageRespone(usersPage);
  }
}
