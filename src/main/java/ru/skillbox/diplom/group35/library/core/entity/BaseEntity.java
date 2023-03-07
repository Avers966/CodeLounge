package ru.skillbox.diplom.group35.library.core.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class BaseEntity {
    private UUID id;
    private boolean isDeleted;
}
