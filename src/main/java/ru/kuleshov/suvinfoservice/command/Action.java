package ru.kuleshov.suvinfoservice.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Action {
    DEFAULT("DEFAULT"),
    FIND("ВВОД ФИО"),
    ADD_EVENT("ДОБАВИТЬ"),
    DELETE_EVENT("УДАЛИТЬ"),
    BACK("НАЗАД"),
    UNDEFINED_ACTION("UNDEFINED");

    private final String nameButton;

    public static Action from(String action) {
        for (Action value : Action.values()) {
            if (value.getNameButton().equalsIgnoreCase(action)) {
                return value;
            }
        }
        return UNDEFINED_ACTION;
    }
}
