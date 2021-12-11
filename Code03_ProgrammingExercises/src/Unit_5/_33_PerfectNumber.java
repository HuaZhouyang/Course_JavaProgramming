package Unit_5;

public class _33_PerfectNumber {
    public static void main(String[] args) {
        for (int i = 1; i < 10000; i++) {
            int lim = i / 2, sum = 0;
            for (int j = 1; j <= lim; j++) {
                if (i % j == 0) {
                    sum += j;
                }
            }
            if (sum == i) {
                System.out.println(i);
            }
        }
    }
}
