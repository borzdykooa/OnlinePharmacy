package by.itacademy.formatter;

import by.itacademy.util.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class IdFormat {

    private static final String PATTERN = "\\d+";

    public static Long format(String value) {
        Long id = null;
        if (!StringUtil.isEmpty(value)) {
            try {
                Pattern pattern = Pattern.compile(PATTERN);
                Matcher matcher = pattern.matcher(value);
                if (matcher.find()) {
                    id = Long.parseLong(value);                }

            } catch (DateTimeParseException e) {
            }
        }

        return id;
    }
}
