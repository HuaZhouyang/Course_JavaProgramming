package Unit_9;

import java.util.Scanner;

/**
 * 9.11
 */
public class LinearEquation {
    private final double a, b, c, d, e, f, denominator;

    public LinearEquation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        denominator = a * d - b * c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public double getE() {
        return e;
    }

    public double getF() {
        return f;
    }

    public boolean isSolvable() {
        return denominator != 0;
    }

    public double getX() {
        return (e * d - b * f) / denominator;
    }

    public double getY() {
        return (a * f - e * c) / denominator;
    }
}

class LinearEquationTest {
    public static void main(String[] args) {
        double a, b, c, d, e, f;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter nums:");
        a = sc.nextDouble();
        b = sc.nextDouble();
        c = sc.nextDouble();
        d = sc.nextDouble();
        e = sc.nextDouble();
        f = sc.nextDouble();
        LinearEquation le = new LinearEquation(a, b, c, d, e, f);
        boolean solvable = le.isSolvable();
        if (solvable) {
            System.out.println(le.getX());
            System.out.println(le.getY());
        } else {
            System.out.println("The equation has no solution.");
        }
    }
}