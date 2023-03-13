package ru.skillbox.diplom.group35.library.core.dto.base;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDto implements Serializable {

    private UUID id;

    private Boolean isDeleted;
}

