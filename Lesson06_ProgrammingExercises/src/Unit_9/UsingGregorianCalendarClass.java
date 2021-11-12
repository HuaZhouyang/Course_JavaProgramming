package Unit_9;

import java.util.GregorianCalendar;

/**
 * 9.5
 */
public class UsingGregorianCalendarClass {
    public static void main(String[] args) {
        GregorianCalendar cal = new GregorianCalendar();
        // task 1
        System.out.println(cal.get(GregorianCalendar.YEAR));
        System.out.println(cal.get(GregorianCalendar.MONTH) + 1);
        System.out.println(cal.get(GregorianCalendar.DAY_OF_MONTH));
        // task 2
        cal.setTimeInMillis(1234567898765L);
        System.out.println(cal.get(GregorianCalendar.YEAR));
        System.out.println(cal.get(GregorianCalendar.MONTH) + 1);
        System.out.println(cal.get(GregorianCalendar.DAY_OF_MONTH));

    }
}
