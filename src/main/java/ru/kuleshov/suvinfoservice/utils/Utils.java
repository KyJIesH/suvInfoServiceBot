package ru.kuleshov.suvinfoservice.utils;

import jakarta.annotation.Nullable;

public class Utils {

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
}
