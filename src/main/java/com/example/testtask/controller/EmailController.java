package com.example.testtask.controller;

import com.example.testtask.controller.documentation.EmailControllerDocumentation;
import com.example.testtask.service.EmailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@RequestMapping("/api/email")
@SecurityRequirement(name = "Test-service")
public class EmailController implements EmailControllerDocumentation {

  private final EmailService emailService;

  @DeleteMapping()
  public ResponseEntity<Void> deleteEmail(
      @RequestParam Long userId,
      @NotBlank(message = "Email не может быть пустым")
      @Email(message = "Неверный формат email")
      @RequestParam String email) {
    emailService.deleteEmail(userId, email);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/update")
  public ResponseEntity<Void> updateEmail(@RequestParam Long userId,
      @NotBlank(message = "Email не может быть пустым")
      @Email(message = "Неверный формат email")
      @RequestParam String oldEmail,
      @NotBlank(message = "Email не может быть пустым")
      @Email(message = "Неверный формат email")
      @RequestParam String newEmail) {
    emailService.updateEmail(userId, oldEmail, newEmail);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/add")
  public ResponseEntity<Void> addEmail(@RequestParam Long userId,
      @NotBlank(message = "Email не может быть пустым")
      @Email(message = "Неверный формат email")
      @RequestParam String email) {
    emailService.addEmail(userId, email);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
