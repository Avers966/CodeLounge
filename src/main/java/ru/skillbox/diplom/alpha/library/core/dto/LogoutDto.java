package ru.skillbox.diplom.alpha.library.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * LogoutDto
 *
 * @author Sergey Marenin
 */
@Data
@AllArgsConstructor
@Schema(description = "Dto выхода из аккуанта")
public class LogoutDto {

  @Schema(description = "Сообщение о выполнении", example = "Ок")
  private String message;
}
