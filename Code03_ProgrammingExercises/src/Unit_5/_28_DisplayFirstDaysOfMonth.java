package Unit_5;

import java.util.Scanner;

public class _28_DisplayFirstDaysOfMonth {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int year = input.nextInt(), w = input.nextInt();
        int[] months = {31,28,31,30,31,30,31,31,30,31,30,31};
        String[] strMonth = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] week = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        months[1] += _27_DisplayLeapYears.isLeapYear(year) ? 1 : 0;
        System.out.println(strMonth[0] + " 1 , " + year + " is " + week[w]);
        for (int j = 1; j < months.length; ++j) {
            for (int i = 1; i <= months[j]; i++) {
                w = (w + 1) % 7;
            }

            System.out.println(strMonth[j] + " 1 , " + year + " is " + week[w]);
        }
    }
}
