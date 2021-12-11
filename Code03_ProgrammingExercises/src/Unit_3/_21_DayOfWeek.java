package Unit_3;

import java.util.Scanner;

public class _21_DayOfWeek {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter year: (e.g., 2012): ");
        int y = sc.nextInt();
        System.out.println("Enter month: 1-12:");
        int m = sc.nextInt();
        System.out.println("Enter the day of the month: 1-31:");
        int q = sc.nextInt();

        if (m <= 2) {
            m = m + 12;
            y = y - 1;
        }
        int j = y / 100, k = y % 100;
        int h = (q + 26 * (m + 1) / 10 + k + k / 4 + j / 4 + 5 * j) % 7;
        String[] week = new String[]{"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        System.out.println("Day of the week is " + week[h]);
    }
}

/*
Enter year: (e.g., 2012):
Enter month: 1-12:
Enter the day of the month: 1-31:
Day of the week is
 */