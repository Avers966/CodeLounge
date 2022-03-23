package ru.skillbox.diplom.alpha.library.core.config;

/**
 * Permission
 *
 * @author Sergey Marenin
 */
public enum Permission {
  USER("user:write"),
  MODERATE("user:moderate"),
  ADMINISTRATION("user:admin");

  private final String permission;

  Permission(String permission) {
    this.permission = permission;
  }

  public String getPermission() {
    return permission;
  }
}
