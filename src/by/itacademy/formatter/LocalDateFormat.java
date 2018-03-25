package by.itacademy.formatter;

import by.itacademy.util.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LocalDateFormat {
    //для форматирования дат всех моих типов

    private static final String PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public static LocalDate format(String value) {
        LocalDate localDate = null;
        if (!StringUtil.isEmpty(value)) {
            try {
                localDate = LocalDate.parse(value, FORMATTER);
            } catch (DateTimeParseException e) {
            }
        }

        return localDate;
    }

    public static String format(LocalDate value) {
        String formattedValue = null;
        if (value != null) {
            formattedValue = FORMATTER.format(value);
        }

        return formattedValue;
    }
}
