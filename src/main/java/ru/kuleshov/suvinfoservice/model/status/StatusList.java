package ru.kuleshov.suvinfoservice.model.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusList {
    FIX("ОБЪЯВЛЕНО"),
    REMOVED("СНЯТО");

    private final String status;

    public static StatusList from(String status) {
        for (StatusList value : StatusList.values()) {
            if (value.getStatus().equalsIgnoreCase(status)) {
                return value;
            }
        }
        return null;
    }
}
