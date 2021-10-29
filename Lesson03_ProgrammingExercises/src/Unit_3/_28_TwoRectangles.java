package Unit_3;

import java.util.Scanner;

public class _28_TwoRectangles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter r1’s center x-, y-coordinates, width, and height: ");
        float x1 = sc.nextFloat(), y1 = sc.nextFloat();
        float w1 = sc.nextFloat(), h1 = sc.nextFloat();
        System.out.print("Enter r2’s center x-, y-coordinates, width, and height: ");
        float x2 = sc.nextFloat(), y2 = sc.nextFloat();
        float w2 = sc.nextFloat(), h2 = sc.nextFloat();
        System.out.print("r2 ");

        if (Math.abs(x1 - x2) > (w1 + w2) / 2.0 ||
                Math.abs(y1 - y2) > (h1 + h2) / 2.0) {
            // outside
            System.out.print("does not overlap");
        } else if (Math.abs(x1 - x2) < (w1 - w2) / 2.0 ||
                Math.abs(y1 - y2) < (h1 - h2) / 2.0) {
            // inside
            System.out.print("is inside");
        } else {
            //overlap
            System.out.print("overlaps");
        }

        System.out.print(" r1");
    }
}

/*
Enter r1’s center x-, y-coordinates, width, and height: 2.5 4 2.5 43
Enter r2’s center x-, y-coordinates, width, and height: 1.5 5 0.5 3
r2 is inside r1
r2 overlaps r1
r2 does not overlap r1
 */