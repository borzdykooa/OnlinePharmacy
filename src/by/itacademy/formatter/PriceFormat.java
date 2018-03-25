package by.itacademy.formatter;

import by.itacademy.util.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PriceFormat {

    private static final String PATTERN = "\\d+\\.\\d{2}";

    public static Double format(String value) {
        Double price = null;
        if (!StringUtil.isEmpty(value)) {
            try {
                Pattern pattern = Pattern.compile(PATTERN);
                Matcher matcher = pattern.matcher(value);
                if (matcher.find()) {
                    price = Double.parseDouble(value);                }

            } catch (DateTimeParseException e) {
            }
        }

        return price;
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
