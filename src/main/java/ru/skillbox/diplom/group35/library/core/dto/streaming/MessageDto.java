package ru.skillbox.diplom.group35.library.core.dto.streaming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.skillbox.diplom.group35.library.core.dto.base.BaseDto;

/**
 * MessageDto
 *
 * @author Marat Safagareev
 */
@Data
@Schema(description = "Dto сообщения")
@EqualsAndHashCode(callSuper = true)
public class MessageDto extends BaseDto {

  @Schema(description = "Дата и время отправки")
  private ZonedDateTime time;

  @Schema(description = "UUID первого собеседника")
  private UUID conversationPartner1;

  @Schema(description = "UUID второго собеседника")
  private UUID conversationPartner2;

  @Schema(description = "Текст сообщения")
  private String messageText;

  @Schema(description = "Статус прочтения: SENT, READ - отправлен, прочитан")
  private String readStatus;

  @Schema(description = "UUID диалога")
  private UUID dialogId;

  @JsonIgnore
  private Long kafkaTimestamp;
}
