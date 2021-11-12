package Unit_9;

import java.util.Scanner;

/**
 * 9.11
 */
public class LinearEquation {
    private final long a, b, c, d, e, f, denominator;

    public LinearEquation(long a, long b, long c, long d, long e, long f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        denominator = a * d - b * c;
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

    public long getD() {
        return d;
    }

    public long getE() {
        return e;
    }

    public long getF() {
        return f;
    }

    public boolean isSolvable() {
        return denominator != 0;
    }

    public double getX() {
        return (e * d - b * f) * 1.0 / denominator;
    }

    public double getY() {
        return (a * f - e * c) * 1.0 / denominator;
    }
}

class LinearEquationTest {
    public static void main(String[] args) {
        long a, b, c, d, e, f;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter nums:");
        a = sc.nextLong();
        b = sc.nextLong();
        c = sc.nextLong();
        d = sc.nextLong();
        e = sc.nextLong();
        f = sc.nextLong();
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