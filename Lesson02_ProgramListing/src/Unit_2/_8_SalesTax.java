package Unit_2;

import java.util.Scanner;

public class _8_SalesTax {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter purchase amount: ");
        double purchaseAmount = input.nextDouble();

        double tax = purchaseAmount * 0.06;
        System.out.println("Sales tax is $" + (int)(tax * 100) / 100.0);
    }
}
