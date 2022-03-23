package ru.skillbox.diplom.alpha.library.core.dto;

import lombok.Getter;

/**
 * ErrorsDescription
 *
 * @author Sergey Marenin
 */
public enum ErrorsDescription {
  UNAUTHORIZED("Пользователь не авторизован"),
  AN_AUTHORIZATION_CODE_MUST_BE_SUPPLIED("Необходимо указать код авторизации"),
  REDIRECT_URI_MISMATCH("Несоответствие url перенаправления"),
  INVALID_AUTHORIZATION_CODE("Неверный код авторизации"),
  THIS_ACCOUNT_ALREADY_EXIST("Этот аккуант уже зарегистрирован"),
  NAME_IS_INCORRECT("Введены слишком короткие имя или фамилия"),
  PASSWORDS_DO_NOT_MATCH("Пароли не совпадают"),
  PASSWORD_IS_INCORRECT("Введен слишком короткий пароль"),
  BAD_CREDENTIALS("Неверные учетные данные"),
  INCORRECT_EMAIL_FORMAT("Почтовый адрес указан некорректно"),
  INCORRECT_PASSWORD_RECOVERY_CODE("Введен неверный код восстановления пароля"),
  CONFIRMATION_CODE_IS_OUTDATED("Ссылка для восстановления пароля устарела. Вы можете"
          + " <a href=\"/login/restore-password\">запросить ссылку снова</a>");

  @Getter
  private final String description;

  ErrorsDescription(String description) {
    this.description = description;
  }
}
