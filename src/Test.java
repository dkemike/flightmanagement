import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        String dt1 = "Friday 10:12";
        String dt2 = "Saturday 10:12";
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("EEEE mm:ss");
            inputFormatter.parse(dt1);

            SimpleDateFormat outputFormat = new SimpleDateFormat("E mm:ss");

            Date date1 = new SimpleDateFormat("EEEE mm:ss").parse(dt1);
            Date date2 = new SimpleDateFormat("EEEE mm:ss").parse(dt2);
            System.out.println("d1: " + date1.getDay());
            System.out.println("d2: " + date2.getDay());
            System.out.println("compare two date: " + date1.compareTo(date2));

        } catch (Exception e) {
            System.out.println("Invalid date format");
        }
    }

}
