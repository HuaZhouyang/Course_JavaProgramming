package Unit_5;

import java.util.Scanner;

public class _17_ShowPyramid {
    public static void main(String[] args) {
        System.out.print("Enter the number of lines (1-15): ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = n; j > i; j--) {
                System.out.print(j > 9 ? "   " : "  ");
            }
            for (int j = i; j >= 2; j--) {
                System.out.print(j + " ");
            }
            System.out.print("1");
            for (int j = 2; j <= i; j++) {
                System.out.print(" " + j);
            }
            System.out.println();
        }

    }
}
