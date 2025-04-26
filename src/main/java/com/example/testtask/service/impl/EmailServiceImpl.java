package com.example.testtask.service.impl;

import com.example.testtask.entity.EmailData;
import com.example.testtask.entity.User;
import com.example.testtask.repository.EmailDataRepository;
import com.example.testtask.repository.UserRepository;
import com.example.testtask.service.EmailService;
import com.example.testtask.util.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final EmailDataRepository emailDataRepository;
  private final UserRepository userRepository;
  private final Validator validator;

  public void deleteEmail(Long userId, String email) {
    validator.validateEmailNotLast(userId, email);
    emailDataRepository.deleteEmailDataByUser_IdAndEmail(userId, email);
  }

  public void updateEmail(Long userId, String oldEmail, String newEmail) {
    validator.validateEmailExist(oldEmail);
    validator.validateEmailIfExist(newEmail);
    validator.validateUserExist(userId);
    EmailData emailData = emailDataRepository.getEmailDataByUser_IdAndEmail(userId, oldEmail);
    emailData.setEmail(newEmail);
    emailDataRepository.save(emailData);
  }

  public void addEmail(Long userId, String email) {
    validator.validateEmailIfExist(email);
    validator.validateUserExist(userId);
    User user = userRepository.getUserById(userId);
    emailDataRepository.save(
        EmailData.builder()
            .user(user)
            .email(email)
            .build());
  }
}
