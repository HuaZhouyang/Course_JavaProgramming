package Unit_3;

import java.util.Scanner;

public class _19_CalculateTrianglePerimeter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt(),
            b = scanner.nextInt(),
            c = scanner.nextInt();
        if (a + b > c && a + c > b && b + c > a) {
            System.out.println(a+b+c);
        } else {
            System.out.println("Invalid Input!");
        }
    }
}
