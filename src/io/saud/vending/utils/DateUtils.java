package io.saud.vending.utils;

import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private final static String DATE_FORMAT = "yyyyMMdd";
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static String parseDateToString(LocalDate date) {
        return date.format(DATE_TIME_FORMATTER);
    }

}
