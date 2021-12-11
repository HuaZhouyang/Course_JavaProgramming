package Unit_5;

import java.util.Scanner;

public class _29_DisplayCalendar {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int year = input.nextInt(), w = input.nextInt();
//        year = 2013;w = 2;
        int[] months = {31,28,31,30,31,30,31,31,30,31,30,31};
        String[] strMonth = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
//        String[] week = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        months[1] += _27_DisplayLeapYears.isLeapYear(year) ? 1 : 0;

        for (int j = 0; j < months.length; ++j) {
            System.out.println("\t\t\t" + strMonth[j] + " " + year);
            System.out.println("————————————————————————————————————");
            System.out.println("\tSun\tMon\tTue\tWed\tThu\tFri\tSat");
            for (int i = 0; i < w; i++) {
                System.out.print("\t");
            }
            for (int i = 1; i <= months[j]; i++) {
                System.out.printf("\t%3d",i);
                w = (w + 1) % 7;
                if (w == 0) System.out.println();
            }
            System.out.println();
        }
    }
}
