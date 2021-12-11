package Unit_9;

import java.util.Scanner;

/**
 * 9.10
 */
public class QuadraticEquation {
    private final long a, b, c, discriminant;

    public QuadraticEquation(long a, long b, long c) {
        this.a = a;
        this.b = b;
        this.c = c;
        discriminant = b * b - 4 * a * c;
    }

    public long getA() {
        return a;
    }

    public long getB() {
        return b;
    }

    public long getC() {
        return c;
    }

    public long getDiscriminant() {
        return discriminant;
    }

    public double getRoot1() {
        if (discriminant < 0) return 0;
        return (-b + Math.sqrt(discriminant) / 2 / a);
    }

    public double getRoot2() {
        if (discriminant < 0) return 0;
        return (-b - Math.sqrt(discriminant) / 2 / a);
    }
}

class QuadraticEquationTest {
    public static void main(String[] args) {
        long a, b, c;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a, b, and c:");
        a = sc.nextLong();
        b = sc.nextLong();
        c = sc.nextLong();
        QuadraticEquation qe = new QuadraticEquation(a, b, c);
        long disc = qe.getDiscriminant();
        if (disc > 0) {
            System.out.println(qe.getRoot1());
            System.out.println(qe.getRoot2());
        } else if (disc == 0) {
            System.out.println(qe.getRoot1());
        } else {
            System.out.println("The equation has no roots.");
        }
    }
}