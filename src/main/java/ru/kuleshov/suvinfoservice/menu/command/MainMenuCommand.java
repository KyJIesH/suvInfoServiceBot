package ru.kuleshov.suvinfoservice.menu.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MainMenuCommand {
    DEFAULT("DEFAULT"),
    INFO("ФУНКЦИОНАЛ"),
    LIST_PEOPLE("РАСХОД ЛС"),
    ENTRANCE("НАЧАЛО РАБОТЫ"),
    ADMIN("АДМИНИСТРИРОВАНИЕ"),
    UNDEFINED_MAIN("UNDEFINED");

    private final String nameButton;

    public static MainMenuCommand from(String menu) {
        for (MainMenuCommand value : MainMenuCommand.values()) {
            if (value.getNameButton().equalsIgnoreCase(menu)) {
                return value;
            }
        }
        return UNDEFINED_MAIN;
    }
}
