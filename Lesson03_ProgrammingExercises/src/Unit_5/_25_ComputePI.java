package Unit_5;

public class _25_ComputePI {
    public static void main(String[] args) {
        for (int i = 10000; i <= 100000; i += 10000) {
            double pi = 0, sign = 1;
            for (int j = 1; j <= i; j++) {
                pi += sign / (2 * j - 1);
                sign *= -1;
            }
            System.out.println(4 * pi);
        }
    }
}
