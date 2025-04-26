package com.example.testtask.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {

  @Bean
  @ServiceConnection
  PostgreSQLContainer<?> postgresContainer() {
    return new PostgreSQLContainer<>("postgres:latest");
  }

//  @Autowired
//  private KeyPair keyPair;

//  @Bean
//  @Primary
//  public AuthFeign authFeignTest() {
//    return new AuthFeign() {
//
//      @Override
//      public String getEncodedPublicKey() {
//        try {
//          return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
//        } catch (Exception e) {
//          log.error(e.getMessage(), e);
//          return null;
//        }
//      }
//
//      @Override
//      public boolean isUserBlacklisted(UUID userId) {
//        return false;
//      }
//
//      @Override
//      public ResponseEntity<UserAuthDto> getCurrentUser(String token, String refreshToken) {
//        return null;
//      }
//    };
//  }

//  @Bean
//  public KeyPair keyPair() throws NoSuchAlgorithmException {
//    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
//    keyPairGenerator.initialize(2048);
//    return keyPairGenerator.generateKeyPair();
//  }

}
