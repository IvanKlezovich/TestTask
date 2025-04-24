package com.example.testtask.secure.dto;

public record LoginRequest(String email,
                           String phone,
                           String password) {
}
