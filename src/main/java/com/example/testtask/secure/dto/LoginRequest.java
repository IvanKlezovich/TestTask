package com.example.testtask.secure.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Неверный формат email")
    String email,
    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 8, max = 50, message = "Пароль должен быть от 8 до 50 символов")
    String password) {
}
