package ru.kuleshov.suvinfoservice.menu.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionCommand {
    DEFAULT("DEFAULT"),
    FIND("ВВОД ФИО"),
    ADD_EVENT("ДОБАВИТЬ"),
    DELETE_EVENT("УДАЛИТЬ"),
    BACK("НАЗАД"),
    UNDEFINED_ACTION("UNDEFINED");

    private final String nameButton;

    public static ActionCommand from(String action) {
        for (ActionCommand value : ActionCommand.values()) {
            if (value.getNameButton().equalsIgnoreCase(action)) {
                return value;
            }
        }
        return UNDEFINED_ACTION;
    }
}
