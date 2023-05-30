package ru.skillbox.diplom.group35.library.core.dto.streaming;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.Data;

/**
 * StreamingMessageDto
 *
 * @author Marat Safagareev
 */
@Data
@Schema(description = "Dto обертки сообщения")
public class StreamingMessageDto<T> {

  @Schema(description = "Тип вложенного сообщения (\"message\"/\"notification\")")
  private String type;

  @Schema(description = "UUID собеседника")
  private UUID recipientId;

  @Schema(description = "Dto сообщения")
  @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
  private T data;
}
