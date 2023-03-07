package ru.skillbox.diplom.group35.library.core.dto.base;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class BaseSearchDto {
    private UUID id;
    private boolean isDeleted;
}
