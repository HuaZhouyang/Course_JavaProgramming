package Unit_3;

import java.util.Random;

public class _04_RandomMonth {
    public static void main(String[] args) {
        String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        Random random = new Random(System.currentTimeMillis());
        int month = random.nextInt(12);
        System.out.println(months[month]);
    }
}
