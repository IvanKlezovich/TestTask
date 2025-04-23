package com.example.testtask.service.impl;

import com.example.testtask.entity.EmailData;
import com.example.testtask.entity.User;
import com.example.testtask.repository.EmailDataRepository;
import com.example.testtask.repository.UserRepository;
import com.example.testtask.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final EmailDataRepository emailDataRepository;
  private final UserRepository userRepository;

  public void deleteEmail(Long userId, String email) {
    emailDataRepository.deleteEmailDataByUser_IdAndEmail(userId, email);
  }

  public void updateEmail(Long userId, String oldEmail, String newEmail) {
    EmailData emailData = emailDataRepository.getEmailDataByUser_IdAndEmail(userId, oldEmail);
    emailData.setEmail(newEmail);
    emailDataRepository.save(emailData);
  }

  public void addEmail(Long userId, String email) {
    User user = userRepository.getUserById(userId);
    emailDataRepository.save(
        EmailData.builder()
            .user(user)
            .email(email)
            .build());
  }
}
