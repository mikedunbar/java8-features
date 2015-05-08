package mike.java8features.date_api;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class Demo {

    public static void main(String[] args) {
        // Clocks are timezone aware, but millis still timezone independent
        Clock clock = Clock.systemDefaultZone();
        long clockMillis = clock.millis();

        long sysMillis = System.currentTimeMillis();
        System.out.println("clockMillis: " + clockMillis + ", sysMillis: " + sysMillis);

        // Instant also represent a point on the timeline, and can be used to get a legacy Date
        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);

        // Timezones represented by ZoneId objects
        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneId zone1 = ZoneId.of("Indian/Comoro");
        ZoneId zone2 = ZoneId.of("America/Tijuana");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());

        //LocalTime represents time without a Timezone
        LocalTime localTime1 = LocalTime.now(zone1);
        LocalTime localTime2 = LocalTime.now(zone2);

        System.out.println("localTime1: " + localTime1);
        System.out.println("localTime2: " + localTime2);
        System.out.println(localTime1.isBefore(localTime2));
        System.out.println(localTime1.isAfter(localTime2));

        long hoursBetween = ChronoUnit.HOURS.between(localTime1, localTime2);
        long minutesBetween = ChronoUnit.MINUTES.between(localTime1, localTime2);
        System.out.println("Hours between: " + hoursBetween);
        System.out.println("Mins between: " + minutesBetween);

        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println("Late: " + late);

        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                    .ofLocalizedTime(FormatStyle.SHORT)
                    .withLocale(Locale.GERMAN);

        LocalTime parsed = LocalTime.parse("13:37", germanFormatter);
        System.out.println("parsed: " + parsed);

        //LocalDate is analagous to LocalTime, e.g. 5/5/2015
        LocalDate date = LocalDate.of(2015, 5, 5);
        System.out.println("LocalDate: " + date);


    }
}
