package Unit_11;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 11.17
 */
public class PerfectSquare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m, n = 1;
        System.out.print("Enter an integer m: ");
        m = sc.nextInt();
        int tmp = m;

        Map<Integer, Integer> map = new HashMap<>();
        int i = 2;
        while (m != 1) {
            if (m % i == 0) {
                m = m / i;
                if (map.containsKey(i)) {
                    map.put(i, map.get(i) + 1);
                } else {
                    map.put(i, 1);
                }
            } else {
                i++;
            }
        }
        for (i = 0; i < 1000; i++) {
            if (map.containsKey(i) && map.get(i) % 2 == 1)
                n = n * i;
        }

        System.out.println("The smallest number n for m * n to be a perfect square is " + n);
        System.out.println("m * n is " + (tmp * n));
    }
}
