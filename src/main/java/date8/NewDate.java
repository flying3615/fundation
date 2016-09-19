package date8;

import java.time.*;

/**
 * Created by liuyufei on 8/30/16.
 */
public class NewDate {

    public static void main(String[] args) {
        LocalDateTime timePoint = LocalDateTime.now();     // The current date and time
        LocalDate.of(2012, Month.DECEMBER, 12); // from values
        LocalDate.ofEpochDay(150);  // middle of 1970
        LocalTime.of(17, 18); // the train I took home today
        LocalTime.parse("10:15:30"); // From a String

        System.out.println(timePoint);

        LocalDate theDate = timePoint.toLocalDate();
        System.out.println(theDate);
        Month month = timePoint.getMonth();
        System.out.println(month);
        int day = timePoint.getDayOfMonth();
        System.out.println(day);
        timePoint.getSecond();

        System.out.println("---only change the date and year---");

        // Set the value, returning a new object
        LocalDateTime thePast = timePoint.withDayOfMonth(
                10).withYear(2010);
        System.out.println(thePast.getDayOfMonth());
        System.out.println(thePast.getMonth());
        System.out.println(thePast.getYear());

        System.out.println("---get the Shanghai time---");
        LocalDateTime now = LocalDateTime.now();
        ZoneId id = ZoneId.of("Asia/Shanghai");
        ZonedDateTime zoned = ZonedDateTime.of(now, id);
        System.out.println(zoned);

        System.out.println("---get the UTC---");
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        System.out.println(utc);
    }
}
