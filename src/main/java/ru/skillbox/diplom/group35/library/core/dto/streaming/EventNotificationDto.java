package ru.skillbox.diplom.group35.library.core.dto.streaming;

import java.util.UUID;
import lombok.Data;

/**
 * EventNotification
 *
 * @author Marat Safagareev
 */
@Data
public class EventNotificationDto {

  private UUID authorId;
  private UUID receiverId;
  private String notificationType;
  private String content;
}
