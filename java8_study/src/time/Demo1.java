package time;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/22.
 */
public class Demo1 {
    public static void main(String[] args) {
        System.out.println("clock---------------");
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant); // legacy java.util.Date
        System.out.println("TimeZone-------------");
        System.out.println(ZoneId.getAvailableZoneIds());
        // prints all available timezone ids
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
        // ZoneRules[currentStandardOffset=+01:00]
        // ZoneRules[currentStandardOffset=-03:00]
    }
}
