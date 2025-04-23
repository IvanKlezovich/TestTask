package com.example.testtask.repository;

import com.example.testtask.entity.EmailData;
import com.example.testtask.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailDataRepository extends JpaRepository<EmailData, Long> {

  List<EmailData> user(User user);

  void deleteEmailDataByUser_IdAndEmail(Long userId, String email);

  EmailData getEmailDataByUser_IdAndEmail(Long userId, String email);
}
