package ru.kuleshov.suvinfoservice.model.statusPeople;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPeopleList {
    NORMAL("ТЕКУЩЕЕ"),
    DISEASE("БОЛЕЗНЬ"),
    TRIPS("ВЫЕЗД");

    private final String statusPeople;

    public static StatusPeopleList from(String statusPeople) {
        for (StatusPeopleList value : StatusPeopleList.values()) {
            if (value.getStatusPeople().equalsIgnoreCase(statusPeople)) {
                return value;
            }
        }
        return null;
    }
}
