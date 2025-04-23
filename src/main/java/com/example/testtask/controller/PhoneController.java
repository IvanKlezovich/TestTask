package com.example.testtask.controller;

import com.example.testtask.dto.UserDto;
import com.example.testtask.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/phone")
public class PhoneController {

  private final PhoneService phoneService;

  @DeleteMapping()
  public ResponseEntity<Void> deletePhone(
      @RequestParam Long userId,
      @RequestParam String phone) {
    phoneService.deletePhone(userId, phone);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/update")
  public ResponseEntity<Void> updatePhone(@RequestParam Long userId,
      @RequestParam String oldPhone,
      @RequestParam String newPhone) {
    phoneService.updatePhone(userId, oldPhone, newPhone);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/add")
  public ResponseEntity<UserDto> addPhone(@RequestParam Long userId,
      @RequestParam String phone) {
    phoneService.addPhone(userId, phone);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
