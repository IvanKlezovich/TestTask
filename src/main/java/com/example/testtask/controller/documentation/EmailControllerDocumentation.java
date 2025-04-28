package com.example.testtask.controller.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;


@Tag(name = "email", description = "Эндпоинты управления email адресами")
public interface EmailControllerDocumentation {

  @Operation(
      summary = "Удалить email адрес пользователя",
      description = "Удаляет указанный email адрес из профиля пользователя",
      security = @SecurityRequirement(name = "Test-service")
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "204",
          description = "Email успешно удален",
          content = @Content),
      @ApiResponse(
          responseCode = "400",
          description = "Некорректный запрос",
          content = @Content),
      @ApiResponse(
          responseCode = "401",
          description = "Неавторизованный доступ",
          content = @Content),
      @ApiResponse(
          responseCode = "403",
          description = "Нет прав доступа",
          content = @Content)
  })
  ResponseEntity<Void> deleteEmail(
      @Parameter(
          description = "ID пользователя",
          required = true
      ) @RequestParam Long userId,
      @Parameter(
          description = "Email адрес для удаления",
          required = true
      ) @RequestParam String email);

  @Operation(
      summary = "Обновить email адрес пользователя",
      description = "Обновляет существующий email адрес на новый",
      security = @SecurityRequirement(name = "Test-service")
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "Email успешно обновлен",
          content = @Content),
      @ApiResponse(
          responseCode = "400",
          description = "Некорректный запрос",
          content = @Content),
      @ApiResponse(
          responseCode = "401",
          description = "Неавторизованный доступ",
          content = @Content),
      @ApiResponse(
          responseCode = "403",
          description = "Нет прав доступа",
          content = @Content)
  })
  ResponseEntity<Void> updateEmail(
      @Parameter(
          description = "ID пользователя",
          required = true
      ) @RequestParam Long userId,
      @Parameter(
          description = "Текущий email адрес",
          required = true
      ) @RequestParam String oldEmail,
      @Parameter(
          description = "Новый email адрес",
          required = true
      ) @RequestParam String newEmail);

  @Operation(
      summary = "Добавить новый email адрес",
      description = "Добавляет новый email адрес в профиль пользователя",
      security = @SecurityRequirement(name = "Test-service")
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "201",
          description = "Email успешно добавлен",
          content = @Content),
      @ApiResponse(
          responseCode = "400",
          description = "Некорректный запрос",
          content = @Content),
      @ApiResponse(
          responseCode = "401",
          description = "Неавторизованный доступ",
          content = @Content),
      @ApiResponse(
          responseCode = "403",
          description = "Нет прав доступа",
          content = @Content)
  })
  ResponseEntity<Void> addEmail(
      @Parameter(
          description = "ID пользователя",
          required = true
      ) @RequestParam Long userId,
      @Parameter(
          description = "Email адрес для добавления",
          required = true
      ) @RequestParam String email);
}
