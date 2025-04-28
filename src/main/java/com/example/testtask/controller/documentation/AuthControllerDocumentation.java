package com.example.testtask.controller.documentation;

import com.example.testtask.secure.dto.AuthResponse;
import com.example.testtask.secure.dto.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "auth", description = "Эндпоинты аутентификации")
public interface AuthControllerDocumentation {

  @Operation(
      summary = "Выполнить вход в систему",
      description = "Авторизует пользователя и возвращает токен доступа",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешная авторизация",
              content = @Content(schema = @Schema(implementation = AuthResponse.class))),
          @ApiResponse(
              responseCode = "401",
              description = "Неверные учетные данные"),
          @ApiResponse(
              responseCode = "400",
              description = "Некорректный запрос")
      }
  )
  ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request);
}
