package ru.kuleshov.suvinfoservice.menu.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminMenuCommand {
    DEFAULT("DEFAULT"),
    ADD_USER("ДОБАВИТЬ ПОЛЬЗОВАТЕЛЯ"),
    DELETE_USER("УДАЛИТЬ ПОЛЬЗОВАТЕЛЯ"),
    CREATE_ADMIN("ВЫДАТЬ ПРАВА АДМИНИСТРАТОРА"),
    BACK("НАЗАД"),
    UNDEFINED_ADMIN("UNDEFINED");

    private final String nameButton;

    public static AdminMenuCommand from(String menu) {
        for (AdminMenuCommand value : AdminMenuCommand.values()) {
            if (value.getNameButton().equalsIgnoreCase(menu)) {
                return value;
            }
        }
        return UNDEFINED_ADMIN;
    }
}
