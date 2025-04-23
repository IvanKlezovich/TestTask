package com.example.testtask.service;

public interface EmailService {

  void deleteEmail(Long userId, String email);

  void updateEmail(Long userId, String oldEmail, String newEmail);

  void addEmail(Long userId, String email);

}
