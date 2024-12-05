package ru.kuleshov.suvinfoservice.model.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleList {
    ADMIN("АДМИНИСТРАТОР"),
    USER("ПОЛЬЗОВАТЕЛЬ");

    private final String role;

    public static RoleList from(String role) {
        for (RoleList value : RoleList.values()) {
            if (value.getRole().equalsIgnoreCase(role)) {
                return value;
            }
        }
        return null;
    }
}
