package ru.skillbox.diplom.group35.library.core.dto.streaming;

import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * EventNotification
 *
 * @author Marat Safagareev
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class EventNotificationDto {

  private UUID authorId;
  private UUID receiverId;
  private String notificationType;
  private String content;
}
