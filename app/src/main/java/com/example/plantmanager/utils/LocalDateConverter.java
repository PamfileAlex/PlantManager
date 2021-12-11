package com.example.plantmanager.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RequiresApi(api = Build.VERSION_CODES.O)
public final class LocalDateConverter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String ToString(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        try {
            return localDate.format(formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    public static LocalDate FromString(String localDate) {
        try {
            return LocalDate.parse(localDate, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
