package com.example.testtask.repository;

import com.example.testtask.entity.PhoneData;
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
}
