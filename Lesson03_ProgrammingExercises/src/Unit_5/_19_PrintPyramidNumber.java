package Unit_5;

public class _19_PrintPyramidNumber {
    public static void main(String[] args) {
        int level = 0;
        for (int i = 1; i <= 128; i <<= 1) {
            level++;
            //4
            for (int j = 0; j < 8 - level; j++) {
                System.out.printf(j == 0 ? "%s" : "%4s", " ");
            }
            for (int j = 1; j < i; j <<= 1) {
                System.out.printf(j == 1 && i == 128 ? "%d" : "%4d", j);
            }
            System.out.printf("%4d", i);
            for (int j = i >> 1; j > 0; j >>= 1) {
                System.out.printf("%4d", j);
            }
            System.out.println();
        }
    }
}
