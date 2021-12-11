package Unit_3;

import java.util.Scanner;

public class _27_PointInsideTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a point’s x- and y-coordinates: ");
        float x = sc.nextFloat(), y = sc.nextFloat();
        System.out.print("The point is");
        // y=-1/2x+100
//        if (x <= 0 || x >= 200
//        || y <= 0 || y >= -0.5 * x + 100) {
        if (!(y < -0.5 * x + 100 && x > 0 && y > 0)) {
            System.out.print(" not");
        }
        System.out.print(" in the triangle");
    }
}
