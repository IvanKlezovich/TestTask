package com.example.testtask.service.impl;

import com.example.testtask.entity.PhoneData;
import com.example.testtask.entity.User;
import com.example.testtask.repository.PhoneDataRepository;
import com.example.testtask.repository.UserRepository;
import com.example.testtask.service.PhoneService;
import com.example.testtask.util.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

  private final PhoneDataRepository phoneDataRepository;
  private final UserRepository userRepository;
  private final Validator validator;

  public void deletePhone(Long userId, String phone) {
    validator.validatePhoneNumberNotLast(userId, phone);
    phoneDataRepository.deletePhoneDataByUser_IdAndPhone(userId, phone);
  }

  public void updatePhone(Long userId, String oldPhone, String newPhone) {
    validator.validatePhoneNumberExist(oldPhone);
    validator.validatePhoneNumberIfExist(newPhone);
    validator.validateUserExist(userId);
    PhoneData emailData = phoneDataRepository.getPhoneDataByUser_IdAndPhone(userId, oldPhone);
    emailData.setPhone(newPhone);
    phoneDataRepository.save(emailData);
  }

  public void addPhone(Long userId, String phone) {
    validator.validatePhoneNumberIfExist(phone);
    validator.validateUserExist(userId);
    User user = userRepository.getUserById(userId);
    phoneDataRepository.save(PhoneData.builder()
        .phone(phone)
        .user(user)
        .build());
  }
}
