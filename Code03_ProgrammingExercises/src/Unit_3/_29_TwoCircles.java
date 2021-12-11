package Unit_3;

import java.util.Scanner;

public class _29_TwoCircles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter circle1’s center x-, y-coordinates, and radius:");
        float x1 = sc.nextFloat(), y1 = sc.nextFloat(), r1 = sc.nextFloat();
        System.out.print("Enter circle2’s center x-, y-coordinates, and radius:");
        float x2 = sc.nextFloat(), y2 = sc.nextFloat(), r2 = sc.nextFloat();
        float dist = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
        System.out.print("circle2 ");
        if (dist >= (r1 + r2) * (r1 + r2)) {
            // outside
            System.out.print("does not overlap");
        } else if (dist <= (r1 - r2) * (r1 - r2)) {
            // inside
            System.out.print("is inside");
        } else {
            // overlaps
            System.out.print("overlaps");
        }
        System.out.print(" circle1");
    }
}

/*
Enter circle1’s center x-, y-coordinates, and radius:
Enter circle2’s center x-, y-coordinates, and radius:
 */