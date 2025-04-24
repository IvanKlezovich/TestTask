package com.example.testtask.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNT")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "USER_ID", nullable = false)
  private User user;

  @Column(precision = 19, scale = 2)
  @NotNull(message = "Баланс не может быть null")
  @DecimalMin(value = "0.00", message = "Баланс не может быть отрицательным")
  private BigDecimal balance;

  @Column(precision = 19, scale = 2)
  @NotNull(message = "Баланс не может быть null")
  @DecimalMin(value = "0.00", message = "Баланс не может быть отрицательным")
  private BigDecimal startDeposit;
}
