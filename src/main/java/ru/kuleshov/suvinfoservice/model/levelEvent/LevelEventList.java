package ru.kuleshov.suvinfoservice.model.levelEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LevelEventList {
    GOOD("ПОЛОЖИТЕЛЬНОЕ"),
    NORMAL("ТЕКУЩАЯ ЗАПИСЬ"),
    TERRIBLE("ГРУБОЕ НАРУШЕНИЕ");

    private final String level;

    public static LevelEventList from(String level) {
        for (LevelEventList value : LevelEventList.values()) {
            if (value.getLevel().equalsIgnoreCase(level)) {
                return value;
            }
        }
        return null;
    }
}
