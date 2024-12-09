package ru.kuleshov.suvinfoservice.utils;

import jakarta.annotation.Nullable;
import ru.kuleshov.suvinfoservice.model.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Utils {

    public static String getDateFormatter(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return dateTime.format(formatter);
    }

    @Nullable
    public static Long convertsStringToLong(@Nullable String str) {
        if (str == null) {
            return null;
        }

        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Nullable
    public static String[] parsString(@Nullable String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        str = str.trim().toLowerCase();
        return str.split(" ");
    }

    public static String viewEvents(List<Event> events) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Event event : events) {
            sb.append(i).append(". ").append(getDateFormatter(event.getDataEvent())).append("\n");
            sb.append("Автор записи: ").append(event.getOwner().getName()).append("\n");
            sb.append("Назначение: ").append(event.getLevelEvent().getEventLevel().getLevel()).append("\n");
            sb.append("Статус: ").append(event.getStatusEvent().getStatus().getStatusEvent()).append("\n");
            sb.append(event.getDescription()).append("\n\n");
            i++;
        }

        return sb.toString();
    }
}