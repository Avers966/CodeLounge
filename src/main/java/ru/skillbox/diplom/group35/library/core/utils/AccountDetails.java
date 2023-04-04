package ru.skillbox.diplom.group35.library.core.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AccountDetails {
    private UUID id;
    private String email;
}
