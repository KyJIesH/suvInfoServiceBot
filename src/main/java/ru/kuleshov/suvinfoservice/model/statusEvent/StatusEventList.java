package ru.kuleshov.suvinfoservice.model.statusEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEventList {
    FIX("ОБЪЯВЛЕНО"),
    REMOVED("СНЯТО");

    private final String statusEvent;

    public static StatusEventList from(String statusEvent) {
        for (StatusEventList value : StatusEventList.values()) {
            if (value.getStatusEvent().equalsIgnoreCase(statusEvent)) {
                return value;
            }
        }
        return null;
    }
}
