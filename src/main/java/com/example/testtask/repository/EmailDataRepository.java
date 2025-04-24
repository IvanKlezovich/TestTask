package com.example.testtask.repository;

import com.example.testtask.entity.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmailDataRepository extends JpaRepository<EmailData, Long> {

  @Modifying
  @Query("delete from EmailData ed where ed.user.id = :userId and ed.email = :email")
  void deleteEmailDataByUser_IdAndEmail(@Param("userId") Long userId,
      @Param("email") String email);

  EmailData getEmailDataByUser_IdAndEmail(Long userId, String email);
}
