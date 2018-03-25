package by.itacademy.formatter;

import by.itacademy.util.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QuantityFormat {

    private static final String PATTERN = "\\d+";

    public static Long format(String value) {
        Long quantity = null;
        if (!StringUtil.isEmpty(value)) {
            try {
                Pattern pattern = Pattern.compile(PATTERN);
                Matcher matcher = pattern.matcher(value);
                if (matcher.find()) {
                    quantity = Long.parseLong(value);                }

            } catch (DateTimeParseException e) {
            }
        }

        return quantity;
    }

//    public static String format(LocalDate value) {
//        String formattedValue = null;
//        if (value != null) {
//            formattedValue = FORMATTER.format(value);
//        }
//
//        return formattedValue;
//    }
}
