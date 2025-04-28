package com.example.testtask.repository;

import com.example.testtask.entity.PhoneData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {

  @Modifying
  @Query("delete from PhoneData pd where pd.user.id = :userId and pd.phone = :phone")
  void deletePhoneDataByUser_IdAndPhone(@Param("userId") Long userId,
      @Param("phone") String phone);

  PhoneData getPhoneDataByUser_IdAndPhone(Long userId, String phone);

  boolean existsPhoneDataByPhone(
      @Pattern(regexp = "^\\d{11}$", message = "Телефон должен быть в формате 11 цифр") @NotBlank(message = "Телефон не может быть пустым") String phone);

  @Query("SELECT COUNT(p) > 1 FROM PhoneData p WHERE p.user.id = :userId AND p.phone = :phone")
  boolean countPhoneDataByUser_IdAndPhoneMoreThan1(Long userId,
      @Pattern(regexp = "^\\d{11}$", message = "Телефон должен быть в формате 11 цифр") @NotBlank(message = "Телефон не может быть пустым") String phone);
}
