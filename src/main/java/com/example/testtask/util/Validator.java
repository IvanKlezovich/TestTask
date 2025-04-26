package com.example.testtask.util;

import com.example.testtask.exception.ApiException;
import com.example.testtask.exception.EntityExistsException;
import com.example.testtask.repository.EmailDataRepository;
import com.example.testtask.repository.PhoneDataRepository;
import com.example.testtask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Validator {

    private final PhoneDataRepository phoneDataRepository;
    private final UserRepository userRepository;
    private final EmailDataRepository emailDataRepository;

    public void validateEmailNotLast(Long userId, String email) {
        if (!phoneDataRepository.countPhoneDataByUser_IdAndPhoneMoreThan1(userId, email)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "У пользователя должен быть хотя бы 1 email");
        }
    }

    public void validateEmailIfExist(String email) {
        if (emailDataRepository.existsEmailDataByEmail(email)) {
            throw new EntityExistsException("Такой email уже существует");
        }
    }

    public void validateEmailExist(String email) {
        if (!emailDataRepository.existsEmailDataByEmail(email)) {
            throw new EntityExistsException("Такого email не существует");
        }
    }

    public void validateUserExist(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityExistsException("Такого пользователя не существует");
        }
    }

    public void validatePhoneNumberNotLast(Long userId, String phone) {
        if (!phoneDataRepository.countPhoneDataByUser_IdAndPhoneMoreThan1(userId, phone)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "У пользователя должен быть хотя бы 1 телефон");
        }
    }

    public void validatePhoneNumberIfExist(String phoneNumber) {
        if (phoneDataRepository.existsPhoneDataByPhone(phoneNumber)) {
            throw new EntityExistsException("Такой телефон уже существует");
        }
    }

    public void validatePhoneNumberExist(String phoneNumber) {
        if (!phoneDataRepository.existsPhoneDataByPhone(phoneNumber)) {
            throw new EntityExistsException("Такого телефона не существует");
        }
    }
}
