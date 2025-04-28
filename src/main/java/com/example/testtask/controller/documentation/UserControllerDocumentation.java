package com.example.testtask.controller.documentation;

import com.example.testtask.dto.PageResponseDto;
import com.example.testtask.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "user", description = "Операции с пользователями")
@Tag(name = "transfer", description = "Операции с переводами")
public interface UserControllerDocumentation {
  @Operation(
      summary = "Поиск пользователей по фильтрам",
      description = "Поиск пользователей с возможностью фильтрации по различным критериям. " +
          "Возвращает отсортированный список пользователей с пагинацией.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Успешный поиск",
              content = @Content(schema = @Schema(implementation = PageResponseDto.class))),
          @ApiResponse(
              responseCode = "400",
              description = "Некорректные параметры поиска",
              content = @Content),
          @ApiResponse(
              responseCode = "401",
              description = "Неавторизованный доступ",
              content = @Content)
      }
  )
  ResponseEntity<PageResponseDto<UserDto>> getUsersByFilter(
      @Parameter(
          description = "Дата рождения для фильтрации",
          required = false)
      @RequestParam(required = false) LocalDate dateOfBirth,

      @Parameter(
          description = "Телефон для фильтрации",
          required = false)
      @RequestParam(required = false) String phone,

      @Parameter(
          description = "Имя для фильтрации",
          required = false)
      @RequestParam(required = false) String name,

      @Parameter(
          description = "Email для фильтрации",
          required = false)
      @Email(message = "Неверный формат email")
      @RequestParam(required = false) String email,

      @Parameter(
          description = "Номер страницы для пагинации",
          required = false)
      @RequestParam(defaultValue = "0") Integer page,

      @Parameter(
          description = "Количество элементов на странице",
          required = false)
      @RequestParam(defaultValue = "10") Integer size);

  @Operation(
      summary = "Перевод денег между пользователями",
      description = "Выполняет перевод денег от одного пользователя к другому. " +
          "Возвращает статус операции.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Перевод успешно выполнен",
              content = @Content),
          @ApiResponse(
              responseCode = "400",
              description = "Некорректная сумма или неверные ID",
              content = @Content),
          @ApiResponse(
              responseCode = "401",
              description = "Неавторизованный доступ",
              content = @Content),
          @ApiResponse(
              responseCode = "404",
              description = "Пользователи не найдены",
              content = @Content)
      }
  )
  ResponseEntity<Void> moneyTransfer(
      @Parameter(
          description = "ID отправителя",
          required = true)
      @RequestParam Long userIdFrom,

      @Parameter(
          description = "ID получателя",
          required = true)
      @RequestParam Long userIdTo,

      @Parameter(
          description = "Сумма перевода",
          required = true)
      @RequestParam BigDecimal money);
}
