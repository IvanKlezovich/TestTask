package com.example.testtask.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 500)
  @NotBlank(message = "Имя пользователя не может быть пустым")
  private String name;

    @Column(name = "DATE_OF_BIRTH")
    @NotNull(message = "Дата рождения обязательна")
    @Past(message = "Дата рождения не может быть в будущем")
  private LocalDate birthday;

  @Column(length = 500)
  @NotBlank(message = "Пароль не может быть пустым")
  @Size(min = 8, max = 500, message = "Пароль должен быть от 8 до 500 символов")
  private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("user")
  private List<PhoneData> phoneData;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<EmailData> emailData;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Account account;
}
