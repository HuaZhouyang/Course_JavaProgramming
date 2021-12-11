package Unit_3;

import java.util.Scanner;

public class _23_PointInsideRectangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float x = sc.nextFloat(), y = sc.nextFloat();
        System.out.printf("Point (%.1f, %.1f) is", x, y);
        if (Math.abs(x) > 5 || Math.abs(y) > 2.5) {
            System.out.print(" not");
        }
        System.out.print(" in the rectangle");
    }
}
