package ru.skillbox.diplom.alpha.library.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * LogoutRs
 *
 * @author Sergey Marenin
 */
@Setter
@Getter
@JsonInclude(Include.NON_NULL)
@Schema(description = "Ответ на запрос выхода из аккуанта")
public class LogoutRs {

  @Schema(description = "Ошибка по запросу", example = "UNAUTHORIZED")
  private Errors error;

  @Schema(description = "Описание ошибки", example = "BAD_CREDENTIALS")
  @JsonProperty(value = "error_description")
  private ErrorsDescription errorDescription;

  @Schema(description = "Метка времени", example = "1644234125000")
  private Long timestamp;

  private LogoutDto data;
}
