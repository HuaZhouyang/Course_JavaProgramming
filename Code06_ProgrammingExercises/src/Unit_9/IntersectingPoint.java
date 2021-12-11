package Unit_9;

import java.util.Scanner;

/**
 * 9.12
 */
public class IntersectingPoint {
    public static void main(String[] args) {
        System.out.println("Please enter four points:");
        /*
        y1 - (x1-x2)/(y1-y2)*x1 + (x1-x2)/(y1-y2)*x = y;
        y1^2 - y1y2 - x1^2 + x1x2 + (x1-x2)x = (y1-y2)y;
        (y1+x1)(y1-x1)
        */
        Scanner sc = new Scanner(System.in);
        double x1 = sc.nextDouble(),y1 = sc.nextDouble();
        double x2 = sc.nextDouble(),y2 = sc.nextDouble();
        double x3 = sc.nextDouble(),y3 = sc.nextDouble();
        double x4 = sc.nextDouble(),y4 = sc.nextDouble();

        double k1 = (x1-x2)/(y1-y2), k2 = (x3-x4)/(y3-y4);
        LinearEquation le = new LinearEquation(
                k1, -1, y1-k1*x1, k2, -1, y3-k2*x3);
        System.out.println(le.getX());
    }
}
