package Unit_5;

public class _26_ComputeE {
    public static void main(String[] args) {
        for (int i = 10000; i <= 100000; i += 10000) {
            double e = 1, denominator = 1;
            for (int j = 1; j <= i; j++) {
                denominator *= j;
                e += 1 / (denominator);
            }
            System.out.println(e);
        }
    }
}
