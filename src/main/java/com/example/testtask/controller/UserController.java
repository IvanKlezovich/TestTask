package com.example.testtask.controller;

import com.example.testtask.controller.documentation.UserControllerDocumentation;
import com.example.testtask.dto.UserDto;
import com.example.testtask.service.UserService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController implements UserControllerDocumentation {

  private final UserService userService;

  @GetMapping("/search/")
  public Page<UserDto> getUsersByFilter(@RequestParam(required = false) LocalDate dateOfBirth,
      @RequestParam(required = false) String phone,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String email,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer size) {
    return userService.getUsersDtoByFilterData(dateOfBirth, phone, name, email, page, size);
  }

  @GetMapping()
  public ResponseEntity<List<UserDto>> getAll() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @GetMapping("/")
  public ResponseEntity<UserDto> getUserById(@RequestParam Long userId) {
    return ResponseEntity.ok(userService.getUser(userId));
  }
}
