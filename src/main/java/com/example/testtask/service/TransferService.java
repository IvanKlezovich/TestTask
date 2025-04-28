package com.example.testtask.service;

import java.math.BigDecimal;

public interface TransferService {

  void transfer(Long userIdFrom, Long userIdTo, BigDecimal money);
}
