package ru.kuleshov.suvinfoservice.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MainMenu {
    DEFAULT("DEFAULT"),
    INFO("ФУНКЦИОНАЛ"),
    LIST_PEOPLE("РАСХОД ЛС"),
    ENTRANCE("НАЧАЛО РАБОТЫ"),
    UNDEFINED_MAIN("UNDEFINED");

    private final String nameButton;

    public static MainMenu from(String menu) {
        for (MainMenu value : MainMenu.values()) {
            if (value.getNameButton().equalsIgnoreCase(menu)) {
                return value;
            }
        }
        return UNDEFINED_MAIN;
    }
}
