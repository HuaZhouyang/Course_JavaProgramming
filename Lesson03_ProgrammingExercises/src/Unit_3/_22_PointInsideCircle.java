package Unit_3;

import java.util.Scanner;

public class _22_PointInsideCircle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a point with two coordinates: ");
        float x = sc.nextFloat(), y = sc.nextFloat();
        System.out.printf("Point (%.1f, %.1f) is", x, y);
        if (x * x + y * y > 100) {
            System.out.print(" not");
        }
        System.out.print(" in the circle");
    }
}

/*
Enter a point with two coordinates: 4 5
Point (4.0, 5.0) is in the circle
Enter a point with two coordinates: 9 9
Point (9.0, 9.0) is not in the circle
 */