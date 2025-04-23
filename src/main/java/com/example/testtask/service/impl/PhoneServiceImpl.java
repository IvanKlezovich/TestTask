package com.example.testtask.service.impl;

import com.example.testtask.entity.PhoneData;
import com.example.testtask.entity.User;
import com.example.testtask.repository.PhoneDataRepository;
import com.example.testtask.repository.UserRepository;
import com.example.testtask.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

  private final PhoneDataRepository phoneDataRepository;
  private final UserRepository userRepository;

  public void deletePhone(Long userId, String phone) {
    phoneDataRepository.deletePhoneDataByUser_IdAndPhone(userId, phone);
  }

  public void updatePhone(Long userId, String oldPhone, String newPhone) {
    PhoneData emailData = phoneDataRepository.getPhoneDataByUser_IdAndPhone(userId, oldPhone);
    emailData.setPhone(newPhone);
    phoneDataRepository.save(emailData);
  }

  public void addPhone(Long userId, String phone) {
    User user = userRepository.getUserById(userId);
    phoneDataRepository.save(PhoneData.builder()
        .phone(phone)
        .user(user)
        .build());
  }
}
