package ru.skillbox.diplom.group35.library.core.dto.base;

import lombok.Data;

import java.util.UUID;

@Data
public class BaseDto {
    private UUID id;
    private boolean isDeleted;
}
