package com.example.testtask.controller;

import com.example.testtask.controller.documentation.UserControllerDocumentation;
import com.example.testtask.dto.PageResponseDto;
import com.example.testtask.dto.UserDto;
import com.example.testtask.service.TransferService;
import com.example.testtask.service.UserService;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController implements UserControllerDocumentation {

  private final UserService userService;
  private final TransferService transferService;
  //  private final AuthenticationService authService;

  @GetMapping(value = "/search/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PageResponseDto<UserDto>> getUsersByFilter(
      @RequestParam(required = false) LocalDate dateOfBirth,
      @RequestParam(required = false) String phone,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String email,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer size) {
    return ResponseEntity.ok(
        userService.getUsersDtoByFilterData(dateOfBirth, phone, name, email, page, size));
  }

  @PostMapping("/transfer")
  public ResponseEntity<Void> moneyTransfer(
      @RequestParam Long userIdFrom,
      @RequestParam Long userIdTo,
      @RequestParam BigDecimal money) {
    transferService.transfer(userIdFrom, userIdTo, money);
    return ResponseEntity.ok().build();
  }

//  @PostMapping("/login")
//  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
//    AuthResponse response = authService.login(request);
//    return ResponseEntity.ok(response);
//  }
}
