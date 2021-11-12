package Unit_9;

import java.util.Date;

/**
 * 9.3
 */
public class UsingDateClass {
    public static void main(String[] args) {
        Date date = new Date();
        for (long i = 10000; i <= 100000000000L; i *= 10) {
            date.setTime(i);
            System.out.println(date.toString());
        }
    }
}
