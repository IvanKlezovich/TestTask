package com.example.testtask.service;

public interface PhoneService {

  void deletePhone(Long userId, String phone);

  void updatePhone(Long userId, String oldPhone, String newPhone);

  void addPhone(Long userId, String phone);
}
