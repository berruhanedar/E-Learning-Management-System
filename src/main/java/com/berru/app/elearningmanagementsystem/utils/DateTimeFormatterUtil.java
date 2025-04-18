package com.berru.app.elearningmanagementsystem.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterUtil {

    public static String convertEpochMillisToFormattedDate(String epochMillis) {
        long epochTime = Long.parseLong(epochMillis);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(epochTime),
                ZoneId.systemDefault()
        );

        return format(localDateTime, "yyyy-MM-dd HH:mm:ss");
    }

    private static String format(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}

