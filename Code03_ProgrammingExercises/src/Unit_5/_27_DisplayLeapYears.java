package Unit_5;

public class _27_DisplayLeapYears {
    public static void main(String[] args) {
        int cnt = 0, counter = 0;
        for (int i = 101; i <= 2100; i++) {
            if (isLeapYear(i)) {
                counter++;
                if (++cnt < 10){
                    System.out.print(i + " ");
                } else {
                    System.out.println(i);
                    cnt = 0;
                }
            }
        }
        System.out.println("\n" + counter);
    }
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year %100 != 0 || year % 400 == 0;
    }
}
