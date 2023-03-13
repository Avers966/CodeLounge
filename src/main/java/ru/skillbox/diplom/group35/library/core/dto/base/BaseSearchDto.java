package ru.skillbox.diplom.group35.library.core.dto.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseSearchDto implements Serializable {

    private UUID id;

    private Boolean isDeleted;
}

