package com.example.testtask.repository;

import com.example.testtask.entity.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {

  void deletePhoneDataByUser_IdAndPhone(Long userId, String phone);

  PhoneData getPhoneDataByUser_IdAndPhone(Long userId, String phone);
}
