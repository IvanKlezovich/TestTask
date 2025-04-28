package com.example.testtask.controller.documentation;

import com.example.testtask.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "phone", description = "Эндпоинты управления телефонами")
public interface PhoneControllerDocumentation {

  @Operation(
      summary = "Удалить телефон пользователя",
      description = "Удаляет указанный телефон из профиля пользователя",
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "Телефон успешно удален",
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
      }
  )
  ResponseEntity<Void> deletePhone(
      @Parameter(
          description = "ID пользователя",
          required = true
      ) @RequestParam Long userId,
      @Parameter(
          description = "Телефон для удаления",
          required = true
      ) @RequestParam String phone);

  @Operation(
      summary = "Обновить телефон пользователя",
      description = "Обновляет существующий телефон на новый",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Телефон успешно обновлен",
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
      }
  )
  ResponseEntity<Void> updatePhone(
      @Parameter(
          description = "ID пользователя",
          required = true
      ) @RequestParam Long userId,
      @Parameter(
          description = "Текущий телефон",
          required = true
      ) @RequestParam String oldPhone,
      @Parameter(
          description = "Новый телефон",
          required = true
      ) @RequestParam String newPhone);

  @Operation(
      summary = "Добавить новый телефон",
      description = "Добавляет новый телефон в профиль пользователя",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Телефон успешно добавлен",
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
      }
  )
  ResponseEntity<UserDto> addPhone(
      @Parameter(
          description = "ID пользователя",
          required = true
      ) @RequestParam Long userId,
      @Parameter(
          description = "Телефон для добавления",
          required = true
      ) @RequestParam String phone);
}
