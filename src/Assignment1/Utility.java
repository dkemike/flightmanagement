package Assignment1;

import java.time.format.DateTimeFormatter;

public class Utility {

    public static final String LONG_DAY_TIME_PATTERN = "EEEE mm:ss";
    public static DateTimeFormatter longDayTimeFormatter = DateTimeFormatter.ofPattern(LONG_DAY_TIME_PATTERN);

    /**
     * check whether a string is a numeric
     * @param s pothential number string
     * @return whether is a number
     */
    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
