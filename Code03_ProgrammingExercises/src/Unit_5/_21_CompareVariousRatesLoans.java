package Unit_5;

import java.util.Scanner;

public class _21_CompareVariousRatesLoans {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Loan Amount: ");
        double loanAmount = input.nextDouble();
//        loanAmount = 10000;
        System.out.print("Number of Years: ");
        int numberOfYears = input.nextInt();
//        numberOfYears = 5;

        System.out.println("Interest Rate\t\tMonthly Payment\t\tTotal Payment");
        for (double annualInterestRate = 5; annualInterestRate <= 8; annualInterestRate += 0.125) {
            double monthlyInterestRate = annualInterestRate / 1200;
            double monthlyPayment = loanAmount * monthlyInterestRate /
                    (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
            double totalPayment = monthlyPayment * numberOfYears * 12;
            System.out.printf("%.3f%%\t\t\t\t%.2f\t\t\t\t%.2f\n", annualInterestRate, monthlyPayment, totalPayment);
        }
    }
}

/*
Loan Amount:
Number of Years:

Interest Rate     Monthly Payment      Total Payment
 */